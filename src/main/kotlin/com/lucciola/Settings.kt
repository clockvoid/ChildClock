package com.lucciola

import java.io.File
import com.squareup.moshi.Moshi
import java.io.InputStreamReader

data class Setting(var enable_reset: String)

class Settings(private val settingsFile: File) {
    private val moshi: Moshi = Moshi.Builder().build()
    private lateinit var setting: Setting

    init {
        if (!settingsFile.exists()) {
            settingsFile.createNewFile()
            settingsFile.absoluteFile.bufferedWriter().use { out ->
                out.write(
                        InputStreamReader(this::class.java.classLoader.getResourceAsStream("default_settings.json")).use {
                            it.readText()
                        }
                )
            }
        }
        readFile()
    }

    fun isEnableReset(): Boolean {
        return this.setting.enable_reset == "true"
    }

    fun enableReset() {
        this.setting.enable_reset = "true"
    }

    fun readFile() {
        val jsonString: String = settingsFile.absoluteFile.bufferedReader().use {
            it.readText()
        }
        try {
            val tmp: Setting? = moshi.adapter(Setting::class.java).fromJson(jsonString)
            when (tmp) {
                null -> throw Exception()
                else -> this.setting = tmp
            }
        } catch (e: Exception) {
            this.setting = Setting("false")
            // json syntax is non-available
            // TODO: add behaviors for json syntax error
        }
    }

    fun writeFile() {
        val jsonStr = this.moshi.adapter(Setting::class.java).toJson(this.setting).toString()
        this.settingsFile.absoluteFile.bufferedWriter().use { out ->
            out.write(jsonStr)
        }
    }
}