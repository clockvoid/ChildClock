package com.lucciola

import java.io.File
import com.squareup.moshi.Moshi
import java.io.InputStreamReader
import java.util.*

data class Setting(var enable_reset: Boolean, var alert: Boolean, var alert_time: Array<Int>, var alert_title: String, var alert_header: String, var alert_content: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Setting

        if (enable_reset != other.enable_reset) return false
        if (alert != other.alert) return false
        if (!Arrays.equals(alert_time, other.alert_time)) return false
        if (alert_title != other.alert_title) return false
        if (alert_header != other.alert_header) return false
        if (alert_content != other.alert_content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = enable_reset.hashCode()
        result = 31 * result + alert.hashCode()
        result = 31 * result + Arrays.hashCode(alert_time)
        result = 31 * result + alert_title.hashCode()
        result = 31 * result + alert_header.hashCode()
        result = 31 * result + alert_content.hashCode()
        return result
    }
}

class Settings(private val settingsFile: File) {
    private val moshi: Moshi = Moshi.Builder().build()
    lateinit var setting: Setting
        private set

    init {
        if (!settingsFile.exists()) {
            settingsFile.createNewFile()
            settingsFile.absoluteFile.bufferedWriter(Charsets.UTF_8).use { out ->
                out.write(
                        loadDefault()
                )
            }
        }
        readFile(false)
    }

    private fun loadDefault(): String {
        return InputStreamReader(this::class.java.classLoader.getResourceAsStream("default_settings.json"), Charsets.UTF_8).use {
            it.readText()
        }
    }

    fun isEnableReset(): Boolean {
        return this.setting.enable_reset
    }

    fun enableReset() {
        this.setting.enable_reset = true
    }

    fun readFile(isDefault: Boolean) {
        val jsonString: String = when (isDefault) {
            true -> {
                settingsFile.absoluteFile.bufferedReader(Charsets.UTF_8).use {
                    it.readText()
                }
            }
            false -> loadDefault()
        }
        try {
            val tmp: Setting? = moshi.adapter(Setting::class.java).fromJson(jsonString)
            when (tmp) {
                null -> throw Exception()
                else -> this.setting = tmp
            }
        } catch (e: Exception) {
            this.setting = moshi.adapter(Setting::class.java).fromJson(loadDefault())!!
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