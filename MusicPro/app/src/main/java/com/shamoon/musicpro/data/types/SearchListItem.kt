package com.shamoon.musicpro.data.types

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.Track
import java.io.Serializable

class SearchListItem: BaseObservable,  Serializable {
    var thumb: String? = null
    var query: String? = null
    var title: String? = null
    var album: Album? = null
    var artist: Artist? = null
    var song: Track? = null
    var type: SearchItemType? = null

    constructor() {}
    constructor(
        thumb: String?,
        title: String?,
        album: Album?,
        artist: Artist?,
        song: Track?,
        type: SearchItemType?,
        query: String?
    ) {
        this.thumb = thumb
        this.title = title
        this.album = album
        this.artist = artist
        this.song = song
        this.type = type
        this.query = query
    }



}