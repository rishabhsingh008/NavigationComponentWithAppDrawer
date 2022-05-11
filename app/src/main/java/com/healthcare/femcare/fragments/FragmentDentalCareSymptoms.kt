package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentDentalCareSymptomsBinding
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.Symptoms
import com.healthcare.femcare.models.UserProfile

class FragmentDentalCareSymptoms  : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDentalCareSymptomsBinding.inflate(inflater, container, false).apply {
            val profile = arguments?.get("userProfile") as UserProfile
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails

            usernameTextView.text = profile.name + " symptoms"
            selectAllCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked) {
                    toothpainCheckBox.isChecked = true
                    smellCheckBox.isChecked = true
                    yellowTeethCheckBox.isChecked = true
                    movingTeethCheckBox.isChecked = true
                    backacheCheckBox.isChecked = true
                    othersCheckBox.isChecked = true
                }
            }
            var listOfSymptoms:ArrayList<String> = ArrayList()

            button.setOnClickListener {
                listOfSymptoms.clear()

                if(toothpainCheckBox.isChecked)
                    listOfSymptoms.add(toothpainCheckBox.text.toString())

                if(smellCheckBox.isChecked)
                    listOfSymptoms.add(smellCheckBox.text.toString())

                if(yellowTeethCheckBox.isChecked)
                    listOfSymptoms.add(yellowTeethCheckBox.text.toString())

                if(movingTeethCheckBox.isChecked)
                    listOfSymptoms.add(movingTeethCheckBox.text.toString())

                if(backacheCheckBox.isChecked)
                    listOfSymptoms.add(backacheCheckBox.text.toString())

                if(othersCheckBox.isChecked)
                    listOfSymptoms.add(othersCheckBox.text.toString())

                if(!listOfSymptoms.isEmpty())
                    moveToNext(listOfSymptoms,profile,regDetails)
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

    fun moveToNext(list : ArrayList<String>,profile:UserProfile,reg:RegistrationDetails){
        findNavController().navigate(FragmentDentalCareSymptomsDirections.fragmentDentalToFragmentSubscriptionPlan(reg,profile,Symptoms(list)))
    }
}