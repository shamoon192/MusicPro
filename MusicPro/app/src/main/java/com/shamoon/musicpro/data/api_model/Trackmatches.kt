package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Trackmatches {
    @SerializedName("track")
    @Expose
    var track: List<Track>? = null

}