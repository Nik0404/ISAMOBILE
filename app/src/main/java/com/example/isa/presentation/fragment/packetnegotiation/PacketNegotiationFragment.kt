package com.example.isa.presentation.fragment.packetnegotiation

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
import com.example.isa.common.constants.Constants.KET_PACKET_NEGOTIATION_ID
import com.example.isa.common.constants.FunctionsConstants
import com.example.isa.common.constants.SboName
import com.example.isa.databinding.FragmentPacketNegotiationBinding
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.presentation.activity.main.MainView
import com.example.isa.presentation.adapter.packetnegotiation.PacketNegotiationAdapter
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class PacketNegotiationFragment : BaseFragment(), PacketNegotiationView {

    private lateinit var binding: FragmentPacketNegotiationBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var packetNegotiationAdapter: PacketNegotiationAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: PacketNegotiationPresenter

    @ProvidePresenter
    fun providePresenter(): PacketNegotiationPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .getComponent(javaClass).inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .releaseComponent(javaClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getDataBindingView(R.layout.fragment_packet_negotiation, inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(binding.root)
        binding.btnMenu.setOnClickListener { (activity as? MainView)?.openDrawer() }
        setupRecyclerView()
        setupSwipeRefresh()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun showPackets(packets: List<DisplayPacketNegotiation>) {
        packetNegotiationAdapter.setData(packets)
    }

    override fun showRefreshPacket() {
        binding.swipeRefreshDataPacket.isRefreshing = true
    }

    override fun hideRefreshPacket() {
        binding.swipeRefreshDataPacket.isRefreshing = false
    }

    override fun showError(message: String?) {
        hideRefreshPacket()
        hideProgress()
    }

    override fun notifyItemChanged(index: Int) {
        if (index != -1) {
            packetNegotiationAdapter.notifyItemChanged(index)
        }
    }

    override fun navigateToPacketDetails(packet: DisplayPacketNegotiation) {
        val args = Bundle().apply {
            putInt(KET_PACKET_NEGOTIATION_ID, packet.id)
        }
        navController.navigate(
            R.id.action_packetNegotiationFragment_to_packetNegotiationDetailsFragment,
            args
        )
    }

    private fun setupRecyclerView() {
        packetNegotiationAdapter = PacketNegotiationAdapter().apply {
            setClickListener(object : PacketNegotiationAdapter.OnClickPacketNegotiationListener {
                override fun onClickPacketNegotiation(position: Int) {
                    presenter.onInfoPacketNegotiation(position)
                }

                override fun onApproveClicked(packet: DisplayPacketNegotiation, position: Int) {
                    val functionId = FunctionsConstants.PACKAGE_RECONCILIATION
                    val sboName = SboName.DEV_PACK_CURATORS
                    val packId = packet.packId.toString()
                    val urcCurator = packet.urcCurator.toString()
                    val urcSignTime = packet.urcSignTime

                    presenter.updatePacketSigned(
                        functionId,
                        sboName,
                        packId,
                        urcCurator,
                        urcSignTime
                    )
                }
            })
        }

        with(binding.packetNegotiationItemsList) {
            adapter = packetNegotiationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.updateUI()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshDataPacket.setOnRefreshListener {
            presenter.refreshPacketNegotiation()
        }
    }
}