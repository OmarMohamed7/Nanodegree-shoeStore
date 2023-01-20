package com.udacity.shoestore.ui.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.ShoeViewModel

class ShoeListFragment :Fragment(){

    private val viewModel : ShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ShoeListFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.shoe_list_fragment , container , false)

//        viewModel = ViewModelProvider(this)[ShoeViewModel::class.java]

        viewModel.shoeList.observe(this as LifecycleOwner , Observer{
            for(item in viewModel.shoeList.value!!){
                // assign every item in list to a variable in a view
                val bindItem = ShoeItemBinding.inflate(layoutInflater)
                bindItem.item = item
                binding.shoeListScrollView.addView(bindItem.root)
            }
        })

        binding.addShoeFAB.setOnClickListener{
            val action = ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}