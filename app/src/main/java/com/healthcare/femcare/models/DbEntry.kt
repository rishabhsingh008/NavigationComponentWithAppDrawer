package com.healthcare.femcare.models

data class DbEntry(
    val regDetails:RegistrationDetails,
    val profile:UserProfile,
    val symptoms: Symptoms
)
