package com.example.isa.presentation.fragment.packetnegotiation.taskpacket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.isa.App
import com.example.isa.R
import com.example.isa.common.constants.Constants.KET_PACKET_TASK_ID
import com.example.isa.common.constants.PreferencesConstants
import com.example.isa.databinding.FragmentPacketTaskBinding
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.adapter.packettask.PacketTaskAdapter
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class PacketBaseFragment : BaseFragment(), PacketBaseView {

    private var _binding: FragmentPacketTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    @Inject
    lateinit var packetTaskAdapter: PacketTaskAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: PacketBasePresenter

    @ProvidePresenter
    fun providePresenter(): PacketBasePresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).getComponentsHolder().getComponent(javaClass)
            .inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .releaseComponent(javaClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPacketTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        arguments?.getString(PreferencesConstants.TASK_LIST, "")?.takeIf { it.isNotEmpty() }
            ?.let { id ->
                setupRecyclerView()
                setupClickListeners()
                presenter.loadTask(id)
            } ?: navController.popBackStack()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showTask(task: List<DisplayPacketTask>) {
        packetTaskAdapter.setData(task)
    }

    override fun showError(message: Int) {
        (activity as? MainActivity)?.showSnackbar(message)
    }

    override fun navigateToPacketTaskDetails(packet: DisplayPacketTask) {
        val args = Bundle()
        args.putInt(KET_PACKET_TASK_ID, packet.id)

        navController.navigate(
            R.id.action_packetNegotiationBaseFragment_to_packetTaskDetailsFragment,
            args
        )
    }

    private fun setupClickListeners() {
        binding.btnMenu.setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        packetTaskAdapter = PacketTaskAdapter().apply {
            setClickListener(object : PacketTaskAdapter.OnClickPacketTaskListener {
                override fun onClickPacketTask(position: Int) {
                    presenter.onInfoPacketTask(position)
                }
            })
        }

        binding.packetNegotiationItemsList.apply {
            adapter = packetTaskAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.lockDrawer()
    }

    override fun onPause() {
        (activity as? MainActivity)?.unlockDrawer()
        super.onPause()
    }
}