package com.ramlawi.notes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

@HiltAndroidApp
class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}