package escola.c6.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import escola.c6.challenge.extension.doRequest
import escola.c6.challenge.extension.isEmpty
import escola.c6.challenge.network.CacheLocal
import escola.c6.challenge.network.RetrofitConfig
import escola.c6.challenge.network.model.RepositoryResponse

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<RepositoryResponse>>()
    val repositories: LiveData<List<RepositoryResponse>>
        get() = _repositories

    fun fetchRepositories() {
        if (_repositories.isEmpty()) {
            RetrofitConfig.api.fetchRepositories().doRequest(
                tag = "abc",
                cache = CacheLocal.repository,
                onSuccess = {
                    _repositories.postValue(it.items)
                }
            )
        }
    }
}