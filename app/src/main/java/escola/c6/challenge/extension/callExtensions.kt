package escola.c6.challenge.extension

import escola.c6.challenge.network.cache.Cache
import escola.c6.challenge.network.cache.CacheLocalDisk.exist
import escola.c6.challenge.network.cache.CacheLocalDisk.find
import escola.c6.challenge.network.cache.CacheLocalDisk.save
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T, ENTITY, MODEL> Call<T>.doRequest(
    find: () -> List<ENTITY>,
    save: (List<ENTITY>) -> Unit,
    onSuccess: (List<MODEL>) -> Unit,
    onError: (Throwable) -> Unit = { },
    transformBackendToDataBase: (T) -> List<ENTITY>,
    transformBackendToView: (T) -> List<MODEL>,
    transformDatabaseToView: (List<ENTITY>) -> List<MODEL>
) {
    val results = find()

    if (results.isNotEmpty()) {
        onSuccess(transformDatabaseToView(results))
        return
    }

    enqueueWithCache(
        onSuccess = onSuccess,
        onError = onError,
        storeData = { save(transformBackendToDataBase(it)) },
        transform = transformBackendToView
    )
}

fun <T, MODEL> Call<T>.doRequest(
    tag: String,
    clazz: Class<T>,
    onSuccess: (List<MODEL>) -> Unit,
    onError: (Throwable) -> Unit = { },
    transform: (T) -> List<MODEL>
) {
    if (exist(tag)) {
        onSuccess(transform(find(tag, clazz)))
        return
    }

    enqueueWithCache(
        onSuccess = onSuccess,
        onError = onError,
        storeData = { save(tag, it) },
        transform = transform
    )
}

fun <T, MODEL> Call<T>.doRequest(
    tag: String,
    cache: Cache<T>,
    onSuccess: (List<MODEL>) -> Unit,
    onError: (Throwable) -> Unit = { },
    transform: (T) -> List<MODEL>,
) {
    cache[tag]?.let {
        onSuccess(transform(it))
        return
    }

    enqueueWithCache(
        onSuccess = onSuccess,
        onError = onError,
        storeData = { cache[tag] = it },
        transform = transform
    )
}

private fun <T, MODEL> Call<T>.enqueueWithCache(
    onSuccess: (List<MODEL>) -> Unit,
    onError: (Throwable) -> Unit = { },
    storeData: (T) -> Unit,
    transform: (T) -> List<MODEL>
) {
    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val item = response.body() as T

                if (response.isSuccessful) {
                    onSuccess(transform(item))
                    storeData(item)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}