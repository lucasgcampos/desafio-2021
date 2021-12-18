package escola.c6.challenge.network

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object CacheLocalDisk {

    private lateinit var database: SharedPreferences

    fun initDatabase(context: Context) {
        database = context.getSharedPreferences("meuBancoDeDados", Context.MODE_PRIVATE)
    }

    fun exist(tag: String) = database.contains(tag)

    fun <T> find(tag: String, clazz: Class<T>): T {
        val result = database.getString(tag, "[]")
        return Gson().fromJson(result, clazz)
    }

    fun <T> save(tag: String, data: T) {
        database
            .edit()
            .putString(tag, Gson().toJson(data))
            .apply()
    }

    fun delete(tag: String) {
        database
            .edit()
            .remove(tag)
            .apply()
    }
}
