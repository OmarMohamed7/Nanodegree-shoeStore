package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.AuthType
import com.udacity.shoestore.models.AuthViewModel
import com.udacity.shoestore.models.LoginState

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: LoginFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.loginButton.setOnClickListener {

            val email = binding.editTextTextPersonEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            viewModel.onAuthentication(email, password, AuthType.Login)
        }

        binding.registerButton.setOnClickListener {
            val email = binding.editTextTextPersonEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            viewModel.onAuthentication(email, password, AuthType.Registration)
        }

        viewModel.state.observe(this as LifecycleOwner, Observer { newState ->
            when (newState) {
                LoginState.LoggedIn -> {
                    navigateToWelcome()
                    viewModel.onEventAuthComplete()
                }
                LoginState.Registered -> {
                    navigateToWelcome()
                    viewModel.onEventAuthComplete()

                }
                else -> {}
            }
        })

        return binding.root
    }

    private fun navigateToWelcome() {
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }

    private fun navigateToShoeList() {
//        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
//        findNavController().navigate(action)
    }

}