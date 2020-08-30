package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("results")
    @Expose
    var results: Results? = null

    @SerializedName("error")
    @Expose
    var error: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

}