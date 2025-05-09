package com.example.isa.presentation.fragment.packetnegotiation.infopacket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.isa.App
import com.example.isa.R
import com.example.isa.common.constants.Constants.KET_PACKET_NEGOTIATION_ID
import com.example.isa.common.constants.FunctionsConstants
import com.example.isa.common.constants.PreferencesConstants
import com.example.isa.common.constants.SboName
import com.example.isa.databinding.FragmentPacketNegotiationDetailBinding
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class PacketNegotiationDetailFragment : BaseFragment(), PacketNegotiationDetailView {

    private lateinit var binding: FragmentPacketNegotiationDetailBinding

    private lateinit var navController: NavController
    private var currentPacket: PacketNegotiation? = null

    @Inject
    @InjectPresenter
    lateinit var presenter: PacketNegotiationDetailPresenter

    @ProvidePresenter
    fun providePresenter(): PacketNegotiationDetailPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .getComponent(javaClass)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPacketNegotiationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupClickListeners()
        loadPacketData()
    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .releaseComponent(javaClass)
    }

    private fun setupNavigation() {
        navController = Navigation.findNavController(binding.root)
    }

    private fun setupClickListeners() {
        binding.apply {
            btnBack.setOnClickListener { navController.popBackStack() }
            popupMenu.setOnClickListener { showPopupMenu(it) }
        }
    }

    private fun loadPacketData() {
        arguments?.getInt(KET_PACKET_NEGOTIATION_ID)?.takeIf { it != -1 }?.let { packetId ->
            presenter.loadPacketNegotiationDetail(packetId)
        } ?: navController.popBackStack()
    }

    override fun showPacketDetails(packet: PacketNegotiation) {
        currentPacket = packet
        binding.apply {
            this.packet = packet
            updateTextColor(packet.maxPackChangeSnm)
            executePendingBindings()
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun updateTextColor(maxPackChangeSnm: String?) {
        val context = context ?: return
        val colorRes = when (maxPackChangeSnm) {
            context.getString(R.string.rework) -> R.color.colorPacChangeRevision
            else -> R.color.colorPacChangeError
        }
        binding.tvTaskMaxPackChangeValue.setTextColor(
            ContextCompat.getColor(context, colorRes)
        )
    }

    private fun showPopupMenu(view: View) {
        val packet = currentPacket ?: return

        PopupMenu(requireContext(), view).apply {
            menuInflater.inflate(R.menu.popup_menu, menu)

            configureMenuItems(packet)
            setOnMenuItemClickListener { onMenuItemClick(it, packet) }

            show()
        }
    }

    private fun PopupMenu.configureMenuItems(packet: PacketNegotiation) {
        menu.findItem(R.id.packet_negotiation)?.let { item ->
            if (packet.isSigned == 1) {
                item.title = getString(R.string.no_packet_negotiation_text)
            }
        }
    }

    private fun onMenuItemClick(item: android.view.MenuItem, packet: PacketNegotiation): Boolean {
        return when (item.itemId) {
            R.id.packet_negotiation -> {
                handlePacketNegotiation(packet)
                true
            }

            R.id.package_content -> {
                val args = Bundle().apply { putInt(PreferencesConstants.PACK_ID, packet.packId) }
                navController.navigate(
                    R.id.action_packetNegotiationDetailsFragment_to_packetNegotiationContentFragment,
                    args
                )
                true
            }

            R.id.package_base -> {
                val args =
                    Bundle().apply { putString(PreferencesConstants.TASK_LIST, packet.taskList) }
                navController.navigate(
                    R.id.action_packetNegotiationDetailsFragment_to_packetNegotiationBaseFragment,
                    args
                )
                true
            }

            else -> false
        }
    }

    private fun handlePacketNegotiation(packet: PacketNegotiation) {
        presenter.updatePacketSigned(
            functionId = FunctionsConstants.PACKAGE_RECONCILIATION,
            sboName = SboName.DEV_PACK_CURATORS,
            packId = packet.packId.toString(),
            urcCurator = packet.urcCurator.toString(),
            urcSignTime = packet.urcSignTime
        )
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.lockDrawer()
    }

    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.unlockDrawer()
    }
}