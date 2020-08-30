package com.shamoon.musicpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.ui.GetInfoListener
import com.shamoon.musicpro.ui.album_search.SearchByAlbumFragment
import com.shamoon.musicpro.ui.artist_search.SearchByArtistFragment
import com.shamoon.musicpro.ui.info.InfoFragment
import com.shamoon.musicpro.ui.main.MainFragment
import com.shamoon.musicpro.ui.song_search.SearchBySongFragment

class MainActivity : AppCompatActivity(), MainFragment.SearchFragmentListener, GetInfoListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(), MainFragment::class.java.simpleName)
                    .commitNow()
        }
    }

    override fun onAlbumSearchClick(name: String) {
        //from here I will go to next list of recipes
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchByAlbumFragment.newInstance(name))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }

    override fun onSongSearchClick(name: String) {
        //from here I will go to next list of recipes
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchBySongFragment.newInstance(name))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }

    override fun onArtistSearchClick(name: String) {
        //from here I will go to next list of recipes
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchByArtistFragment.newInstance(name))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }

    override fun onAlbumInfoClick(album: Album) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InfoFragment.newInstance(album))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }

    override fun onSongInfoClick(song: Track) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InfoFragment.newInstance(song))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }

    override fun onArtistInfoClick(artist: Artist) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InfoFragment.newInstance(artist))
            .addToBackStack(SearchByAlbumFragment::class.java.simpleName)
            .commit()
    }
}
