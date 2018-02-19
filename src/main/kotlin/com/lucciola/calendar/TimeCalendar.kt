package com.lucciola.calendar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File
import java.lang.reflect.ParameterizedType
import java.text.DateFormat
import java.util.*

data class Day(val date: String, val time: String)

class TimeCalendar(private val jsonFile: File) {
    var json: String = ""
        private set
    var jsonData: List<Day> = arrayListOf()
        private set
    private val moshi: Moshi = Moshi.Builder().build()
    private val type: ParameterizedType = Types.newParameterizedType(List::class.java, Day::class.java)
    private val adapter: JsonAdapter<List<Day>> = this.moshi.adapter(type)
    private val dateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.JAPAN)

    init {
        this.readFile()
    }

    fun readFile(): String {
        if (!this.jsonFile.exists()) {
            this.jsonFile.createNewFile()
        }
        this.json = this.jsonFile.absoluteFile.bufferedReader(Charsets.UTF_8).use({ it.readText() })
        // Moshi cannot parse empty json
        when (this.json) {
            "" -> this.json = "[]"
        }
        makeDayList()
        return this.json
    }

    fun writeFile() {
        this.jsonFile.absoluteFile.bufferedWriter(Charsets.UTF_8).use({ out -> out.write(this.json) })
    }

    fun addDay(arg0: Date, hour: Int, min: Int, sec: Int) {
        // Must decide data format in this class
        this.jsonData += arrayListOf(Day(this.dateFormat.format(arg0), "%02d:%02d:%02d".format(hour, min, sec)))
    }

    fun searchDate(arg0: Date): String {
        var str = ""
        for (day in jsonData) {
            when (this.dateFormat.format(arg0)) {
                day.date -> str = day.time
            }
        }
        return str
    }


    fun makeJson() {
        val jsonObj = this.adapter.toJson(this.jsonData)
        this.json = jsonObj.toString()
    }

    fun makeDayList() {
        try {
            this.jsonData = adapter.fromJson(this.json)!!
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
}
