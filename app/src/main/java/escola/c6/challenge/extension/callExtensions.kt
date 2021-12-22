package escola.c6.challenge.extension

import escola.c6.challenge.network.cache.Cache
import escola.c6.challenge.network.cache.CacheLocalDisk.exist
import escola.c6.challenge.network.cache.CacheLocalDisk.find
import escola.c6.challenge.network.cache.CacheLocalDisk.save
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.doRequest(
    tag: String,
    clazz: Class<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = { }
) {
    if (exist(tag)) {
        onSuccess(find(tag, clazz))
        return
    }

    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val item = response.body() as T

                if (response.isSuccessful) {
                    save(tag, item)
                    onSuccess(item)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}

fun <T> Call<T>.doRequest(
    tag: String,
    cache: Cache<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = { }
) {
    cache[tag]?.let {
        onSuccess(it)
        return
    }


    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val item = response.body() as T

                if (response.isSuccessful) {
                    cache[tag] = item
                    onSuccess(item)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}