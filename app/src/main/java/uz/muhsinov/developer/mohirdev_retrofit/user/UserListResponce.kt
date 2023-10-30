package uz.muhsinov.developer.mohirdev_retrofit.user

import com.google.gson.annotations.SerializedName

data class UserListResponce(
    @SerializedName("data")
    val userList: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)