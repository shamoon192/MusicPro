package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Artist: Serializable {
    @SerializedName("image")
    @Expose
    var image: List<Image__>? = null

    @SerializedName("listeners")
    @Expose
    var listeners: String? = null

    @SerializedName("mbid")
    @Expose
    var mbid: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("streamable")
    @Expose
    var streamable: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}