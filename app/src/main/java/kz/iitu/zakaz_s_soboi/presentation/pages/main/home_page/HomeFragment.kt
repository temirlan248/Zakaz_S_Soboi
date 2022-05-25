package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kz.iitu.zakaz_s_soboi.databinding.HomeFragmentBinding
import kz.iitu.zakaz_s_soboi.presentation.MainActivity
import kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.CartSharedViewModel
import kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.rv.RestaurantListAdapter
import kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.state.RestaurantListState

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val adapter: RestaurantListAdapter by lazy {
        RestaurantListAdapter().apply {
            onItemClickListener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToRestaurantFragment(it)
                )
            }
        }
    }

    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity.showBottomNav()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
        initRV()

        activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner){}
    }

    private fun initRV() {
        binding.restaurantRecyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.root.setOnRefreshListener {
            viewModel.getRestaurantList()
            binding.root.isRefreshing = false
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.restaurantListState.collectLatest {
                when (it) {
                    is RestaurantListState.Loading -> {
                        activity.startLoading()
                    }
                    is RestaurantListState.Error -> {
                        activity.stopLoading()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    is RestaurantListState.Success -> {
                        activity.stopLoading()
                        adapter.submitList(it.restaurantList)
                    }
                    else -> activity.stopLoading()
                }
            }
        }
    }
}