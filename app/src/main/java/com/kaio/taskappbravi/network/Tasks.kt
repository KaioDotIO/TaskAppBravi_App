package com.kaio.taskappbravi.network

import com.google.gson.annotations.SerializedName

data class Tasks(
    @SerializedName("activity") var activity: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("participants") var participants: Int?,
    @SerializedName("price") var price: Double?,
    @SerializedName("link") var link: String?,
    @SerializedName("key") var key: String?,
    @SerializedName("accessibility") var accessibility: Double?
)