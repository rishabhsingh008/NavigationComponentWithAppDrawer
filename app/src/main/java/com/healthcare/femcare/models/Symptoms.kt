package com.healthcare.femcare.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Symptoms(
    val listOfSymptoms : ArrayList<String>

) : Parcelable
