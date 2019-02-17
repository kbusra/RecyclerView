package com.model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("last_name")
        val lastName: String
)