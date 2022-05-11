package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentRegistrationBinding
import com.healthcare.femcare.databinding.WelcomePopupBinding
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.UserProfile

class FragmentWelcomePopUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = WelcomePopupBinding.inflate(inflater, container, false).apply {
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails
            button.setOnClickListener {
                moveToNext(regDetails)
            }
        }
        return binding.root
    }

    fun moveToNext(data:RegistrationDetails){
        findNavController().navigate(FragmentWelcomePopUpDirections.fragmentWelcomePopupToFragmentProfile(data))
    }
}