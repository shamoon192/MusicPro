package com.shamoon.musicpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shamoon.musicpro.ui.main.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.SearchFragmentListener {

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
        Toast.makeText(this, "onAlbumSearchClick", Toast.LENGTH_SHORT).show()
    }

    override fun onSongSearchClick(name: String) {
        Toast.makeText(this, "onSongSearchClick", Toast.LENGTH_SHORT).show()
    }

    override fun onArtistSearchClick(name: String) {
        Toast.makeText(this, "onArtistSearchClick", Toast.LENGTH_SHORT).show()
    }

    override fun onAlbumInfoClick(albumId: String) {
        Toast.makeText(this, "onAlbumInfoClick", Toast.LENGTH_SHORT).show()
    }

    override fun onSongInfoClick(songId: String) {
        Toast.makeText(this, "onSongInfoClick", Toast.LENGTH_SHORT).show()
    }

    override fun onArtistInfoClick(artistId: String) {
        Toast.makeText(this, "onArtistInfoClick", Toast.LENGTH_SHORT).show()
    }
}
