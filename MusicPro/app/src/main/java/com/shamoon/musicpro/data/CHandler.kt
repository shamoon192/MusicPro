package com.shamoon.musicpro.data

class CHandler {
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun getAPIKey(): String
    external fun getSharedSecret(): String
    external fun getResisteredTo(): String
    external fun getAPIAppName(): String
    external fun getAPIURL(): String
}