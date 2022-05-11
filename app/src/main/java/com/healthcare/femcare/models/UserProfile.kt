package com.healthcare.femcare.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    val name:String,
    val age:String,
    val weight:String,
    val height:String,
    val profession:String,
    val city:String,
    val maritalStatus: String/*,
    val mobile:String,
    val password:String*/
):Parcelable
