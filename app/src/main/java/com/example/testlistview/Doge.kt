package com.example.testlistview

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Doge (
    @SerializedName(value="id") var imagen: String,
    @SerializedName(value="title") var titulo: String,
    @SerializedName(value="body") var description: String) : Serializable