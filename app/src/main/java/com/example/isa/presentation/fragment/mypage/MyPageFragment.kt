package com.example.isa.presentation.fragment.mypage

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
import com.example.isa.databinding.FragmentMyPageBinding
import com.example.isa.domain.entity.local.database.User
import com.example.isa.presentation.activity.main.MainActivity
import com.example.isa.presentation.activity.main.MainView
import com.example.isa.presentation.mvp.BaseFragment
import javax.inject.Inject

class MyPageFragment : BaseFragment(), MyPageView {

    private lateinit var binding: FragmentMyPageBinding

    private lateinit var navController: NavController

    @Inject
    @InjectPresenter
    lateinit var presenter: MyPagePresenter

    @ProvidePresenter
    fun providePresenter(): MyPagePresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getApp(activity?.applicationContext).getComponentsHolder().getComponent(javaClass)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getDataBindingView(R.layout.fragment_my_page, inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        binding.btnMenu.setOnClickListener { (activity as? MainView)?.openDrawer() }
        btnDocSignature()
    }

    override fun setUserData(user: User) {
        binding.user = user
    }

    override fun showSnackbar(resourceId: Int) {
        (activity as? MainActivity)?.showSnackbar(resourceId)
    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .releaseComponent(javaClass)
    }

    private fun btnDocSignature() {
        binding.docSignatureButton.setOnClickListener {
            navController.navigate(
                R.id.action_myPageFragment_to_packetNegotiationFragment
            )
        }
    }

}