package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Albummatches {
    @SerializedName("album")
    @Expose
    var album: List<Album>? = null

}