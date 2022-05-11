package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.healthcare.femcare.databinding.FragmentGoalSettingBinding
import com.healthcare.femcare.databinding.FragmentProfileBinding
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.UserProfile

class FragmentGoalSetting : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGoalSettingBinding.inflate(inflater, container, false).apply {
            val profile = arguments?.get("userProfile") as UserProfile
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails

            //userBMITextView.text = profile.name + " BMI " + (Double.(profile.height)/Integer.parseInt(profile.weight))
            userBMITextView.text = profile.name + " BMI XXX"
            userNameTextView.text = profile.name + " Health Goal"

            button.setOnClickListener {
                if(periodsRadioButton.isChecked)
                    moveToPeriodsCare(profile,regDetails)
                else if(dentalRadioButton.isChecked)
                    moveToDentalCare(profile,regDetails)
                else
                    Toast.makeText(
                        requireActivity().baseContext,
                        "PLease select any checkbox",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        return binding.root
    }

    fun moveToDentalCare(profile:UserProfile,reg:RegistrationDetails){
        findNavController().navigate(FragmentGoalSettingDirections.fragmentGoalSettingToFragmentDentalCare(reg,profile))
    }

    fun moveToPeriodsCare(profile:UserProfile,reg:RegistrationDetails){
        findNavController().navigate(FragmentGoalSettingDirections.fragmentGoalSettingToFragmentPeriodCare(reg,profile))
    }
}