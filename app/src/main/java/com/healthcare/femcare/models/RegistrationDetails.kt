package com.healthcare.femcare.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationDetails(
    val mobile: String,
    val password : String
):Parcelable
