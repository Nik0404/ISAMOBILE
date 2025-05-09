package com.example.isa.presentation.activity.main

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.isa.App
import com.example.isa.R
import com.example.isa.common.extension.addQrDrawerListener
import com.example.isa.databinding.ActivityMainBinding
import com.example.isa.databinding.NavHeaderMainBinding
import com.example.isa.di.activity.main.MainModule
import com.example.isa.domain.entity.local.database.User
import com.example.isa.presentation.mvp.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var navHeaderMainBinding: NavHeaderMainBinding

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.getApp(applicationContext).getComponentsHolder().getComponent(
            javaClass,
            MainModule(applicationContext)
        ).inject(this)
        super.onCreate(savedInstanceState)

        binding = getDataBindengView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navHeaderMainBinding = DataBindingUtil.bind(binding.navView.getHeaderView(0))!!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_isa, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.addQrDrawerListener {
            when (item.itemId) {
                R.id.myPageFragment -> navController.navigate(R.id.action_global_my_page)
                R.id.packetNegotiationFragment -> navController.navigate(R.id.action_global_packet_negotiation)
                R.id.logout -> presenter.logout()
            }
            binding.drawerLayout.removeDrawerListener(it)
        }
        closeDrawer()
        return true
    }

    override fun lockDrawer() = binding.drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer() = binding.drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)

    override fun openDrawer() = binding.drawerLayout.openDrawer(GravityCompat.START)

    override fun closeDrawer() = binding.drawerLayout.closeDrawer(GravityCompat.START)

    override fun setUser(user: User) {
        navHeaderMainBinding.user = user
    }

    override fun showSnackbar(resourceId: Int) = showSnackbar(getString(resourceId))

    override fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed))
        val textView =
            snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        snackbar.show()
    }

    override fun openLoginActivity() {
        Handler().postDelayed({
            navController.navigate(R.id.loginActivity)
            finish()
        }, 250)
    }

    override fun setMenuResource(menuResourceId: Int, checkedItemId: Int) {
        binding.navView.inflateMenu(menuResourceId)
        binding.navView.setCheckedItem(checkedItemId)
    }

    override fun setupNavController(navigationGraphId: Int) {
        navController.setGraph(navigationGraphId)

        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.refreshSessionId()
    }
}
