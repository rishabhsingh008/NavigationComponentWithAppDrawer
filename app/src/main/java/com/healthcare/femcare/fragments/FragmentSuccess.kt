package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentSuccessLayoutBinding
import com.healthcare.femcare.models.UserProfile

class FragmentSuccess : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSuccessLayoutBinding.inflate(inflater, container, false).apply {
            val profile = arguments?.get("userProfile") as UserProfile
            usernameText.text = "Congrats " + profile.name
            button.setOnClickListener {
                moveToHome()
            }
        }
        return binding.root
    }

    fun moveToHome(){
        findNavController().navigate(FragmentSuccessDirections.fragmentSuccessToHome())
    }
}