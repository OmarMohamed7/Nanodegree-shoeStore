package com.udacity.shoestore.ui.addShoeItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import com.udacity.shoestore.models.State

class AddShoeFragment :Fragment(){

    private val viewModel : ShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: AddShoeFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.add_shoe_fragment , container , false)
            // This will create a new instance every time so the data is lost
//        viewModel = ViewModelProvider(this, viewModelFactory {  })[ShoeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.item = Shoe("",0.0 , "" , "")

        binding.cancelButton.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.saveBtn.setOnClickListener{
            val shoeName = binding.edtShoeName.text.toString()
            var size = binding.edtShoeSize.text.toString()
            var shoeSize = size.toDoubleOrNull()
            val shoeCompany = binding.edtShoeCompany.text.toString()
            val shoeDescription = binding.edtShoeDescription.text.toString()

            if (shoeSize == null) {
                shoeSize = 0.0
            }
                viewModel.onEventSave(shoeName,shoeSize , shoeCompany ,shoeDescription )

        }

        viewModel.state.observe(this as LifecycleOwner , Observer{
            when(it){
                State.Saved -> {
//                    val action = AddShoeFragmentDirections.actionAddShoeFragmentToShoeListFragment()
                    findNavController().navigateUp()
                    viewModel.onEventSaveComplete()
                }
                else -> {}
            }

        })

        return binding.root
    }
}