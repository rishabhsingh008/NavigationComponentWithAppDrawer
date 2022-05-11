package com.healthcare.femcare.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.healthcare.femcare.databinding.FragmentSubsciptionPlanBinding
import com.healthcare.femcare.models.DbEntry
import com.healthcare.femcare.models.RegistrationDetails
import com.healthcare.femcare.models.Symptoms
import com.healthcare.femcare.models.UserProfile
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class FragmentSubscriptionPlan : Fragment(), PaymentResultListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Checkout.preload(requireActivity().applicationContext);
        val binding = FragmentSubsciptionPlanBinding.inflate(inflater, container, false).apply {
            val profile = arguments?.get("userProfile") as UserProfile
            val regDetails = arguments?.get("registrationDetails") as RegistrationDetails
            val symptoms = arguments?.get("symptoms") as Symptoms

            buyButton.setOnClickListener { saveData(regDetails,profile,symptoms) }

        }
        return binding.root
    }

    fun saveData(reg:RegistrationDetails,profile:UserProfile,symptoms : Symptoms){


        val database = FirebaseDatabase.getInstance("https://femcare-cdbbd-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val usersDbRef = database.reference.child("Users")
        usersDbRef.push().setValue(DbEntry(reg,profile,symptoms))

        //checkout.setKeyID("<YOUR_KEY_ID>");
        //startPayment()



        findNavController().navigate(FragmentSubscriptionPlanDirections.fragmentSubscriptionPlanToFragmentSuccess(profile))

    }

    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = requireActivity()
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","50000")//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }
}