package escola.c6.challenge.network.cache

import escola.c6.challenge.network.model.RepositoryWrapperResponse

object CacheLocal {

    val repository = Cache<RepositoryWrapperResponse>()
}

class Cache<T> : HashMap<String, T>()
