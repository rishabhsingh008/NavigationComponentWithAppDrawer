package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentPeriodCareSymptomsBinding
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.Symptoms
import com.healthcare.femcare.models.UserProfile

class FragmentPeriodCareSymptoms : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPeriodCareSymptomsBinding.inflate(inflater, container, false).apply {
            val profile = arguments?.get("userProfile") as UserProfile
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails

            userNameTextView.text = profile.name + " symptoms"
            selectAllCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    excessPainCheckBox.isChecked = true
                    irregularPeriodCheckBox.isChecked = true
                    heavyBleedingCheckBox.isChecked = true
                    headacheCheckBox.isChecked = true
                    backacheCheckBox.isChecked = true
                    nauseaCheckBox.isChecked = true
                    crampsCheckBox.isChecked = true
                }
            }

            var listOfSymptoms:ArrayList<String> = ArrayList()

            button.setOnClickListener {

                if(excessPainCheckBox.isChecked)
                    listOfSymptoms.add(excessPainCheckBox.text.toString())

                if(irregularPeriodCheckBox.isChecked)
                    listOfSymptoms.add(irregularPeriodCheckBox.text.toString())

                if(heavyBleedingCheckBox.isChecked)
                    listOfSymptoms.add(heavyBleedingCheckBox.text.toString())

                if(headacheCheckBox.isChecked)
                    listOfSymptoms.add(headacheCheckBox.text.toString())

                if(backacheCheckBox.isChecked)
                    listOfSymptoms.add(backacheCheckBox.text.toString())

                if(nauseaCheckBox.isChecked)
                    listOfSymptoms.add(nauseaCheckBox.text.toString())

                if(crampsCheckBox.isChecked)
                    listOfSymptoms.add(crampsCheckBox.text.toString())

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
        findNavController().navigate(FragmentPeriodCareSymptomsDirections.fragmentPeriodCareToFragmentSubscriptionPlan(reg,profile,Symptoms(list)))
    }
}