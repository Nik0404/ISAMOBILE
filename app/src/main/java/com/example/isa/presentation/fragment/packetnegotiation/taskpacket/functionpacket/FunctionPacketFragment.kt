package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket

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
import com.example.isa.common.constants.Constants.KET_PACKET_TASK_ID
import com.example.isa.databinding.FragmentFunctionPacketBinding
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.adapter.packetfunction.FunctionPacketAdapter
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class FunctionPacketFragment : BaseFragment(), FunctionPacketView {

    private var _binding: FragmentFunctionPacketBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    @Inject
    lateinit var functionAdapter: FunctionPacketAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: FunctionPacketPresenter

    @ProvidePresenter
    fun providePresenter(): FunctionPacketPresenter = presenter

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
        _binding = FragmentFunctionPacketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val taskId = arguments?.getInt(KET_PACKET_TASK_ID, -1) ?: -1
        if (taskId == -1) {
            return
        }

        presenter.loadTask(taskId)
        setupRecyclerView()
        setupClickListeners()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showFunction(function: List<DisplayPacketFunction>) {
        functionAdapter.setData(function)
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.lockDrawer()
    }

    override fun onPause() {
        (activity as? MainActivity)?.unlockDrawer()
        super.onPause()
    }

    private fun setupRecyclerView() {
        binding.packetFunctionList.apply {
            adapter = functionAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setupClickListeners() {
        binding.btnToBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}