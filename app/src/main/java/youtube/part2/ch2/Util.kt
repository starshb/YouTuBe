package youtube.part2.ch2

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException

fun <T> Context.readData(fileName: String, classT: Class<T>): T? {
    return try {
        val inputStream = this.resources.assets.open(fileName)
        val byteArray = ByteArray(inputStream.available())
        inputStream.read(byteArray)
        inputStream.close()
        Gson().fromJson(String(byteArray), classT)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }

}