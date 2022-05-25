package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kz.iitu.zakaz_s_soboi.databinding.RestaurantFragmentBinding
import kz.iitu.zakaz_s_soboi.domain.model.Category
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant
import kz.iitu.zakaz_s_soboi.presentation.MainActivity
import kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.CartSharedViewModel
import kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.RestaurantAdapter
import kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.model.MenuItem

@AndroidEntryPoint
class RestaurantFragment : Fragment() {

    private var _binding: RestaurantFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantViewModel by viewModels()
    private val sharedViewModel: CartSharedViewModel by activityViewModels()

    private val adapter: RestaurantAdapter by lazy {
        RestaurantAdapter().apply {
            onAddClickListener = {
                sharedViewModel.addToCart(it)
            }
            onRemoveClickListener = {
                sharedViewModel.removeFromCart(it)
            }
        }
    }

    private val args: RestaurantFragmentArgs by navArgs()
    private val restaurantId: Int by lazy { args.restaurantId }
    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        sharedViewModel.clear()
        super.onDetach()
    }

    override fun onResume() {
        activity.clearCart()
        super.onResume()
        activity.showBottomNav()
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
        sharedViewModel.createCart(restaurantId)
        binding.recyclerView.adapter = adapter
        setListeners()
        setObservers()
        viewModel.getRestaurant(restaurantId)
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.restaurantState.collectLatest {
                when (it) {
                    is RestaurantState.Loading -> {
                        activity.startLoading()
                    }
                    is RestaurantState.Error -> {
                        activity.stopLoading()
                        activity.showMessage(it.message)
                    }
                    is RestaurantState.Success -> {
                        activity.stopLoading()
                        setData(it.restaurant)
                    }
                    else -> activity.stopLoading()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            sharedViewModel.hasItemsState.collectLatest {
                binding.cartButton.isGone = !it
            }
        }
    }

    private fun setData(restaurant: Restaurant) {
        with(binding) {
            Picasso.get()
                .load(restaurant.imageUrl)
                .into(restaurantImageView)
            restaurantNameTextView.text = restaurant.name
            setRV(restaurant.categoryList)
        }
    }

    private fun setRV(categoryList: List<Category>) {
        val list = mutableListOf<MenuItem>()
        for (category in categoryList) {
            list.add(MenuItem(categoryName = category.name))
            for (product in category.productList) {
                list.add(MenuItem(product = Pair(product, 0)))
            }
        }
        adapter.menuList = list
    }

    private fun setListeners() {
        binding.backImageView.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.cartButton.setOnClickListener {
            findNavController().navigate(
                RestaurantFragmentDirections.actionRestaurantFragmentToCartFragment()
            )
        }
    }

}