package kz.iitu.zakaz_s_soboi.presentation.pages.auth.registration_page

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
import kz.iitu.zakaz_s_soboi.databinding.RegistrationFragmentBinding
import kz.iitu.zakaz_s_soboi.presentation.MainActivity

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    private val activity: MainActivity by lazy {
        requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity.hideBottomNav()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        viewModel.cancel()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        with(binding) {
            collectLatestLifecycleFlow(viewModel.registrationState) {
                when (it) {
                    is RegistrationState.Loading -> {
                        emailInputLayout.isHelperTextEnabled = false
                        passwordInputLayout.isHelperTextEnabled = false
                        reEnterPasswordInputLayout.isHelperTextEnabled = false
                    }
                    is RegistrationState.Error -> {
                        emailInputLayout.helperText = it.emailError
                        passwordInputLayout.helperText = it.passwordError
                        reEnterPasswordInputLayout.helperText = it.reEnteredPasswordError
                        if (!it.error.isNullOrEmpty()) {
                            activity.showMessage(it.error)
                        }
                    }
                    is RegistrationState.Success -> {
                        findNavController().navigateUp()
                        viewModel.resetState()
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun setListeners() {
        with(binding) {
            registrationButton.setOnClickListener {
                val email = emailInputLayout.editText!!.text.toString()
                val password = passwordInputLayout.editText!!.text.toString()
                val reEnteredPassword = reEnterPasswordInputLayout.editText!!.text.toString()

                viewModel.register(email, password, reEnteredPassword)
            }

            loginTextView.setOnClickListener {
                findNavController().navigateUp()
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