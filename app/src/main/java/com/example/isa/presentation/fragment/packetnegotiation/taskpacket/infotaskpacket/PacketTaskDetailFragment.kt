package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.isa.App
import com.example.isa.R
import com.example.isa.common.constants.Constants.KET_PACKET_TASK_ID
import com.example.isa.databinding.FragmentPacketTaskDetailBinding
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class PacketTaskDetailFragment : BaseFragment(), PacketTaskDetailView {

    private var _binding: FragmentPacketTaskDetailBinding? = null
    private val binding get() = _binding!!

    private var task: PacketTask? = null

    private lateinit var navController: NavController

    @Inject
    @InjectPresenter
    lateinit var presenter: PacketTaskDetailPresenter

    @ProvidePresenter
    fun providePresenter(): PacketTaskDetailPresenter = presenter

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
        _binding = FragmentPacketTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        loadTaskData()
        setupClickListeners()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showTaskDetails(task: PacketTask) {
        this.task = task
        binding.apply {
            this.task = task
            executePendingBindings()
        }
    }

    override fun navigationToFunction(id: Int) {
        val args = Bundle()
        args.putInt(KET_PACKET_TASK_ID, id)
        navController.navigate(
            R.id.action_packetTaskDetailsFragment_to_functionPacketFragment, args
        )
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.lockDrawer()
    }

    override fun onPause() {
        (activity as? MainActivity)?.unlockDrawer()
        super.onPause()
    }

    private fun loadTaskData() {
        arguments?.getInt(KET_PACKET_TASK_ID)?.takeIf { it != -1 }?.let { packetId ->
            presenter.getPacketTask(packetId)
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.listFunctionButton.setOnClickListener {
            presenter.onTaskDetail()
        }
    }
}