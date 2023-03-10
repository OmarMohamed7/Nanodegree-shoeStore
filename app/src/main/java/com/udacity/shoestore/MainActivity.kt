package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.ui.shoeList.ShoeListFragmentDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this , R.layout.activity_main)

        Timber.plant(Timber.DebugTree())
        Timber.i("onCreate")



        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
        val navController = findNavController(R.id.fragmentContainerView)
//
        NavigationUI.setupActionBarWithNavController(this , navController)
//
//
        binding.toolbar.setOnMenuItemClickListener{ item ->
            Timber.i(item.toString())
            when(item.itemId){
                R.id.gotoLogin -> {
                    navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                    true
                }
                else -> false
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
