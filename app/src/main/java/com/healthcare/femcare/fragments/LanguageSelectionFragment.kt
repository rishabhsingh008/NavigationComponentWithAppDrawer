package com.healthcare.femcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.healthcare.femcare.databinding.FragmentLanguageSelectionBinding

class LanguageSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLanguageSelectionBinding.inflate(inflater, container, false)
        //findNavController(this).popBackStack()
        binding.englishRadioButton.setOnClickListener {
            navigateToNext()
        }

        binding.hindiRadioButton.setOnClickListener {
            navigateToNext()
        }

        return binding.root
    }

    fun navigateToNext(){
        val direction = LanguageSelectionFragmentDirections.languageSelectionFragmentToFragmentRegistration()
        findNavController(this).navigate(direction)
    }
}