package uz.muhsinov.developer.mohirdev_retrofit.user

import com.google.gson.annotations.SerializedName

data class UserResponce(
    @SerializedName("data")
    val user: User,
    val support: Support
)
