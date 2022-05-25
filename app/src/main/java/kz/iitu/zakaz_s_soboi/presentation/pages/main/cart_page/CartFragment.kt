package kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kz.iitu.zakaz_s_soboi.databinding.CartFragmentBinding
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.presentation.MainActivity
import kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.rv.CartAdapter

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartSharedViewModel by activityViewModels()
    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    private val adapter: CartAdapter by lazy { CartAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemsRecyclerView.adapter = adapter
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.closeImageView.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.makeOrderButton.setOnClickListener {
            viewModel.insertCart()
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.cartState.collectLatest {
                when (it) {
                    is CartState.Loading -> {
                        activity.startLoading()
                    }
                    is CartState.Error -> {
                        activity.stopLoading()
                        activity.showMessage(it.message)
                    }
                    is CartState.Success -> {
                        activity.stopLoading()
                        setData(it.cart)
                    }
                    is CartState.Uploaded -> {
                        findNavController().navigate(
                            CartFragmentDirections.actionCartFragmentToHomeFragment()
                        )
                    }
                    else -> activity.stopLoading()
                }
            }
        }
    }

    private fun setData(cart: Cart) {
        with(binding){
            restaurantNameTextView.text = cart.restaurantName
            restaurantLocationTextView.text = cart.restaurantLocation
            Picasso.get()
                .load(cart.restaurantImage)
                .into(restaurantImageView)

            adapter.cartItems = cart.itemList
        }
    }

}