package com.example.isa.presentation.fragment.packetnegotiation.contentpacket

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
import com.example.isa.common.constants.PreferencesConstants
import com.example.isa.databinding.FragmentPacketContentBinding
import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.adapter.packetcontent.PacketContentAdapter
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class PacketContentFragment : BaseFragment(), PacketContentView {

    private var _binding: FragmentPacketContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    @Inject
    lateinit var packetContentAdapter: PacketContentAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: PacketContentPresenter

    @ProvidePresenter
    fun providePresenter(): PacketContentPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as? App)?.let {
            it.getComponentsHolder().getComponent(javaClass).inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPacketContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        arguments?.getInt(PreferencesConstants.PACK_ID, -1)?.takeIf { it != -1 }?.let { id ->
            setupRecyclerView()
            setupClickListeners()
            presenter.loadPacketContent(id)
        } ?: navController.popBackStack()
    }

    private fun setupRecyclerView() {
        with(binding.packetNegotiationItemsList) {
            adapter = packetContentAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setupClickListeners() {
        binding.btnMenu.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showPacketContent(packetContent: List<DisplayPacketcontent>) {
        packetContentAdapter.setData(packetContent)
    }

    override fun showError(message: Int) {
        (activity as? MainActivity)?.showSnackbar(message)
    }

    override fun onDestroyView() {
        binding.packetNegotiationItemsList.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        (activity?.applicationContext as? App)?.let {
            it.getComponentsHolder().releaseComponent(javaClass)
        }
        super.onDetach()
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