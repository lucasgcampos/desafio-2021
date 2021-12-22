package escola.c6.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import escola.c6.challenge.database.dao.RepositoryDao
import escola.c6.challenge.database.model.RepositoryEntity
import escola.c6.challenge.extension.doRequest
import escola.c6.challenge.network.RetrofitConfig
import escola.c6.challenge.network.cache.CacheLocal
import escola.c6.challenge.network.cache.CacheType
import escola.c6.challenge.network.model.RepositoryWrapperResponse
import escola.c6.challenge.ui.model.Repository

class RepositoryViewModel(private val dao: RepositoryDao) : ViewModel() {

    private val repositoryKey = "some_secret_key"

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories

    fun fetchRepositories(cacheType: CacheType = CacheType.IN_MEMORY) {
        when (cacheType) {
            CacheType.IN_MEMORY -> RetrofitConfig.api.fetchRepositories().doRequest(
                tag = repositoryKey,
                cache = CacheLocal.repository,
                onSuccess = { _repositories.postValue(it) },
                transform = { it.items.map(::Repository) }
            )
            CacheType.SHARED_PREFERENCES -> RetrofitConfig.api.fetchRepositories().doRequest(
                tag = repositoryKey,
                clazz = RepositoryWrapperResponse::class.java,
                onSuccess = { _repositories.postValue(it) },
                transform = { it.items.map(::Repository) }
            )
            CacheType.ROOM -> RetrofitConfig.api.fetchRepositories().doRequest(
                find = { dao.find() },
                save = { dao.save(it) },
                onSuccess = { _repositories.postValue(it) },
                transformBackendToDataBase = { it.items.map(::RepositoryEntity) },
                transformBackendToView = { it.items.map(::Repository) },
                transformDatabaseToView = { it.map(::Repository) },
            )
        }
    }
}

class RepositoryViewModelFactory(private val dao: RepositoryDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepositoryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}