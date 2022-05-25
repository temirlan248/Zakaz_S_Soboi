package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page

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
import kz.iitu.zakaz_s_soboi.databinding.HistoryFragmentBinding
import kz.iitu.zakaz_s_soboi.presentation.MainActivity
import kz.iitu.zakaz_s_soboi.presentation.pages.auth.login_page.LoginFragmentDirections
import kz.iitu.zakaz_s_soboi.presentation.pages.auth.login_page.LoginState
import kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv.HistoryAdapter

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
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
        activity.showBottomNav()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        collectLatestLifecycleFlow(viewModel.historyState) {
            when (it) {
                is HistoryState.Loading -> {
                    activity.startLoading()
                }
                is HistoryState.Error -> {
                    activity.stopLoading()
                    activity.showMessage(it.message)
                }
                is HistoryState.Success -> {
                    activity.stopLoading()
                    adapter.cartList = it.cartList
                }
                is HistoryState.Exit -> {
                    findNavController().navigate(
                        HistoryFragmentDirections.actionHistoryFragmentToLoginFragment()
                    )
                }
                else -> activity.stopLoading()
            }
        }
    }

    private fun setupRv() {
        binding.restaurantRecyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.root.setOnRefreshListener {
            binding.root.isRefreshing = false
            viewModel.getCartList()
        }

        binding.exitImageView.setOnClickListener {
            viewModel.exit()
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