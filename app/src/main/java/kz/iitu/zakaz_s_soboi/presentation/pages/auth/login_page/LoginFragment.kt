package kz.iitu.zakaz_s_soboi.presentation.pages.auth.login_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.databinding.LoginFragmentBinding
import kz.iitu.zakaz_s_soboi.presentation.MainActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()
    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        viewModel.cancel()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        activity.hideBottomNav()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        with(binding) {
            collectLatestLifecycleFlow(viewModel.loginState) {
                when (it) {
                    is LoginState.Loading -> {
                        showLoading()
                        emailInputLayout.isHelperTextEnabled = false
                        passwordInputLayout.isHelperTextEnabled = false
                    }
                    is LoginState.Error -> {
                        hideLoading()
                        val emailError = it.emailError
                        val passwordError = it.passwordError
                        val error = it.message

                        if (emailError.isNotEmpty()) {
                            emailInputLayout.helperText = emailError
                        }

                        if (passwordError.isNotEmpty()) {
                            passwordInputLayout.helperText = passwordError
                        }

                        if (error.isNotEmpty()) {
                            activity.showMessage(error)
                        }
                    }
                    is LoginState.Success -> {
                        hideLoading()
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        )
                        viewModel.resetState()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            activity.startLoading()
            loginButton.isClickable = false
            registerTextView.isClickable = false
            resetTextView.isClickable = false
        }
    }

    private fun hideLoading() {
        with(binding) {
            activity.stopLoading()
            loginButton.isClickable = true
            registerTextView.isClickable = true
            resetTextView.isClickable = true
        }
    }

    private fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                viewModel.login(email, password)
            }

            registerTextView.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
                )
            }

            resetTextView.setOnClickListener {
                activity.showMessage("Not implemented yet!")
            }
        }
    }

    private fun <T> collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

}