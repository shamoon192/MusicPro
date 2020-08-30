package com.shamoon.musicpro.ui

import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.Track

interface GetInfoListener {
    fun onAlbumInfoClick(album: Album)
    fun onSongInfoClick(song: Track)
    fun onArtistInfoClick(artist: Artist)
}