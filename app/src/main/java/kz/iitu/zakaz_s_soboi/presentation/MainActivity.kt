package kz.iitu.zakaz_s_soboi.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kz.iitu.zakaz_s_soboi.databinding.ActivityMainBinding
import kz.iitu.zakaz_s_soboi.presentation.loader.Loader
import kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.CartSharedViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: CartSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (savedInstanceState == null){
            viewModel.loadData()
        }

        Loader.init(this)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.navHostFragment.id
        ) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    fun showBottomNav(){
        if (binding.bottomNavigationView.isGone){
            binding.bottomNavigationView.isGone = false
        }
    }

    fun hideBottomNav(){
        if (!binding.bottomNavigationView.isGone){
            binding.bottomNavigationView.isGone = true
        }
    }

    fun startLoading() = Loader.start()

    fun stopLoading() = Loader.stop()

    fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun showMessage(messageId: Int) =
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()

    fun clearCart() {
        sharedViewModel.clear()
    }
}
