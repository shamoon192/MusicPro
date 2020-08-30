package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpensearchQuery {
    @SerializedName("#text")
    @Expose
    var text: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("startPage")
    @Expose
    var startPage: String? = null

}