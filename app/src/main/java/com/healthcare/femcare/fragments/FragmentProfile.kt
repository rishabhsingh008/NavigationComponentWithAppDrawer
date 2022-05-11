package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentProfileBinding
import com.healthcare.femcare.databinding.WelcomePopupBinding
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.UserProfile

class FragmentProfile : Fragment() {

    val professionList = listOf("--Profession--","HouseWife","Student","Working","Others")
    val cityList = listOf("--City--","Gurgaon","Delhi","Noida","Ghaziabad","Bokaro")
    val maritalStatus = listOf("--Marital Status--","Married","Single")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails

            ageSpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,populateAgeList())
            weightSpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,populateWeightList())
            heightSpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,populateHeightList())
            professionSpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,professionList)
            citySpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,cityList)
            maritalStatusSpinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,maritalStatus)

            button.setOnClickListener {
                if(nameEditText.text.length>0 &&
                    ageSpinner.selectedItemPosition>0 &&
                    weightSpinner.selectedItemPosition>0 &&
                    heightSpinner.selectedItemPosition>0 &&
                    professionSpinner.selectedItemPosition>0 &&
                    citySpinner.selectedItemPosition>0 &&
                    maritalStatusSpinner.selectedItemPosition>0){

                    //if (checkBox.isChecked)
                        moveToNext(UserProfile(nameEditText.text.toString(),
                            ageSpinner.selectedItem.toString(),
                            weightSpinner.selectedItem.toString(),
                            heightSpinner.selectedItem.toString(),
                            professionSpinner.selectedItem.toString(),
                            citySpinner.selectedItem.toString(),
                            maritalStatusSpinner.selectedItem.toString(),
                        ),regDetails)
                    /*else
                        Toast.makeText(
                            requireActivity().baseContext,
                            "PLease check the checkbox",
                            Toast.LENGTH_SHORT
                        ).show()*/
                }
                else
                    Toast.makeText(requireActivity().baseContext,"PLease enter valid values",Toast.LENGTH_SHORT).show()

            }
        }
        return binding.root
    }

    fun populateAgeList():ArrayList<String>{
        val ageList : ArrayList<String> = ArrayList<String>()
        ageList.add("--Age(in years)--")
        for(num in 13..60){
            ageList.add("" + num)
        }
        ageList.add("60+")
        return ageList
    }

    fun populateWeightList():ArrayList<String>{
        val weightList : ArrayList<String> = ArrayList<String>()
        weightList.add("--Weight(in Kg)--")
        for(num in 30..90){
            weightList.add("" + num)
        }
        return weightList
    }

    fun populateHeightList():ArrayList<String>{
        val heightList : ArrayList<String> = ArrayList<String>()
        heightList.add("--Height--")
        for(num in 4..6){
            for(num1 in 0..11)
                heightList.add("" + num + "." + num1)
        }
        return heightList
    }

    fun moveToNext(profile:UserProfile,reg:RegistrationDetails){
        val directions = FragmentProfileDirections.fragmentProfileToFragmentGoalSetting(reg,profile)
        findNavController().navigate(directions)
    }
}