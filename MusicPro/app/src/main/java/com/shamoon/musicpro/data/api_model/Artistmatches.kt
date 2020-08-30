package com.shamoon.musicpro.data.api_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artistmatches {
    @SerializedName("artist")
    @Expose
    var artist: List<Artist>? = null

}