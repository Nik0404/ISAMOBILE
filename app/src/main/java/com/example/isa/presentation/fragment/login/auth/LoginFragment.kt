package com.example.isa.presentation.fragment.login.auth

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.isa.App
import com.example.isa.common.constants.Constants
import com.example.isa.databinding.FragmentLoginBinding
import com.example.isa.presentation.fragment.login.base.BaseLoginFragment
import com.example.isa.presentation.fragment.login.base.BaseLoginPresenter
import javax.inject.Inject

class LoginFragment : BaseLoginFragment(), LoginView {

    companion object {
        fun newInstance(isAfterLogout: Boolean): LoginFragment {
            val args = Bundle()
            args.putBoolean(Constants.KEY_IS_AFTER_LOGOUT, isAfterLogout)

            val fragment = LoginFragment()
            fragment.arguments = args

            return fragment
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = presenter

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ensureDeviceId()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getApp(context).getComponentsHolder()
            .getComponent(javaClass).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginEditText.requestFocus()

        binding.signInButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            presenter.onLoginButtonClicked(login, password)
        }

        presenter.isAfterLogout = arguments!!.getBoolean(Constants.KEY_IS_AFTER_LOGOUT, false)
    }

    override fun hideKeyboard() {
        val inputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)

    }

    override fun onDetach() {
        super.onDetach()
        App.getApp(activity?.applicationContext).getComponentsHolder()
            .releaseComponent(javaClass)
    }

    override fun getRootView(): FrameLayout = binding.rootView

    override fun getProgressBar(): ProgressBar = binding.progressBar

    override fun getTextViewAppVersion(): AppCompatTextView = binding.textViewAppVersion

    override fun getPresenter(): BaseLoginPresenter<*> = presenter

    private fun getTextViewDeviceId(): AppCompatTextView = binding.textViewDeviceId

    override fun setDeviceId(deviceId: String) {
        getTextViewDeviceId().text = deviceId
    }

    private fun ensureDeviceId() {
        var deviceID = presenter.getDeviceId();
        if (deviceID.isNullOrEmpty()) {
            deviceID = Settings.Secure.getString(
                this.context!!.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            presenter.setDeviceId(deviceID)
        }
    }
}
