package com.mononz.skeleton.controller

import android.content.SharedPreferences

class Session(private val sharedPreferences: SharedPreferences) {

    fun getAppOpenCounter(): Int {
        return sharedPreferences.getInt(APP_OPEN_COUNTER, 0)
    }

    fun incrementAppOpenCounter() {
        val count = getAppOpenCounter()
        saveData(sharedPreferences, APP_OPEN_COUNTER, count + 1)
    }

    // =============================================================================================
    // Helper Functions - Save to Preferences
    // =============================================================================================

    private fun <T> saveData(pref: SharedPreferences, key: String, value: T?) {
        val prefsEditor = pref.edit()
        if (value is String || value == null) {
            prefsEditor.putString(key, value as String?)
        } else if (value is Long) {
            prefsEditor.putLong(key, (value as Long?)!!)
        } else if (value is Int) {
            prefsEditor.putInt(key, (value as Int?)!!)
        } else if (value is Boolean) {
            prefsEditor.putBoolean(key, (value as Boolean?)!!)
        } else {
            throw UnsupportedOperationException("Unsupported Data Type")
        }
        prefsEditor.commit()
    }

    // =============================================================================================
    // Helper Functions - Pull from Preferences
    // =============================================================================================

    private fun getDataString(pref: SharedPreferences?, key: String): String? {
        return if (pref != null) {
            pref.getString(key, "")
        } else ""
    }

    private fun getDataLong(pref: SharedPreferences?, key: String): Long {
        return pref?.getLong(key, -1) ?: -1
    }

    private fun getDataBoolean(pref: SharedPreferences?, key: String, default: Boolean): Boolean {
        return pref?.getBoolean(key, default) ?: default
    }

    companion object {

        private const val APP_OPEN_COUNTER = "app_open_counter"
    }
}
