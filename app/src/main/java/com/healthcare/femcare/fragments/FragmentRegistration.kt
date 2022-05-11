package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.healthcare.femcare.databinding.FragmentRegistrationBinding
import android.R
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.DialogCompat
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.models.RegistrationDetails


class FragmentRegistration : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false).apply {
            okButton.setOnClickListener {
                if(editTextPhone.text.length>0 && editTextPassword.text.length>0) {
                    if (okCheckBox.isChecked)
                        moveToNext(RegistrationDetails(editTextPhone.text.toString(),editTextPassword.text.toString()))
                    else
                        Toast.makeText(
                            requireActivity().baseContext,
                            "PLease check the checkbox",
                            Toast.LENGTH_SHORT
                        ).show()
                }
                else
                    Toast.makeText(requireActivity().baseContext,"PLease enter valid values",Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    /*fun showPopUp(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.healthcare.femcare.R.layout.welcome_popup)
        val dialogButton: Button = dialog.findViewById<Button>(com.healthcare.femcare.R.id.button)!! as Button
        dialogButton.setOnClickListener(View.OnClickListener { moveToNext() })

        dialog.show()
    }*/

    private fun moveToNext(details:RegistrationDetails) {
        val direction = FragmentRegistrationDirections.fragmentRegistrationToFragmentWelcomePopup(details)
        findNavController().navigate(direction)
    }
}