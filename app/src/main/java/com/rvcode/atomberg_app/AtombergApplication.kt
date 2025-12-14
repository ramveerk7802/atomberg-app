package com.rvcode.atomberg_app

import android.app.Application
import com.rvcode.atomberg_app.networks.RetrofitClient

class AtombergApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
    }
}