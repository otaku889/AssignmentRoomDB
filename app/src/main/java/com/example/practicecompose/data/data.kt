package com.example.practicecompose.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

data class Users(
    @SerializedName("username")
    val username: String,
    @SerializedName("number")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("uid")
    val uid: Int,
)

data class Orders(
    @SerializedName("distance")
    val distance: String,
    @SerializedName("pickupLocation")
    val pickupLocation: String,
    @SerializedName("dropOffLocation")
    val dropOffLocation: String,
    @SerializedName("orderType")
    val orderType: String,
    @SerializedName("time")
    val orderTime: String,
    @SerializedName("vehicleType")
    val orderVehicle: String,
    @SerializedName("Price")
    val orderPrice: Double,
    @SerializedName("date")
    val orderDate: String,
    @SerializedName("status")
    val orderStatus: String,

    )

@Serializable
data class Order(
    @SerialName("order_id")
    val order_id: Int?,
    @SerialName("created_at")
    val created_at: String?,
    @SerialName("order_type")
    val order_type: String?,
    @SerialName("distance")
    val distance: String?,
    @SerialName("pickup")
    val pickup: String?,
    @SerialName("dropoff")
    val dropoff: String?,
    @SerialName("date")
    val date: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("price")
    val price: Float?,
    @SerialName("status")
    val status: String?,
    @SerialName("driver_uid")
    val driver_uid: String?,
    @SerialName("vehicle_type")
    val vehicle_type: String?




)

@Serializable
data class Accounts(
    @SerialName("id")
    val userUuiD: String,
    @SerialName("created_at")
    val created_at: String,
    @SerialName("username")
    val userName: String,
    @SerialName("email")
    val emails: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("password")
    val password: String,
    @SerialName("user_type")
    val user_type: String,
)

data class Products(
    val order_id: String,
    val created_at: String,
    val order_type: String,
    val distance: String,
    val price: Double,
    val pickup: String,
    val dropoff: String,
    val date: String,
    val time: String,
    val status: String,
    val driver_uid: String,
    val vehicle_type: String
)