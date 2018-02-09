
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

data class Day(val date: String, val time: String)

class TimeCalendar {
    var json: String = ""
        private set
    var jsonData: List<Day> = arrayListOf()
        private set
    private val moshi: Moshi = Moshi.Builder().build()
    private val type: ParameterizedType = Types.newParameterizedType(List::class.java, Day::class.java)
    private val adapter: JsonAdapter<List<Day>> = this.moshi.adapter(type)

    fun addDay(arg: Day) {
        this.jsonData += arrayListOf(arg)
    }

    fun makeJson() {
        val jsonObj = this.adapter.toJson(this.jsonData)
        this.json = jsonObj.toString()
    }

    fun makeDayList() {
        val jsonDataNullable = adapter.fromJson(this.json)
        try {
            this.jsonData = jsonDataNullable!!
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
}
