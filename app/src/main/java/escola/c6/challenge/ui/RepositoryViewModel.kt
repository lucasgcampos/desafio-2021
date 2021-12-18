package escola.c6.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import escola.c6.challenge.extension.doRequest
import escola.c6.challenge.network.RetrofitConfig
import escola.c6.challenge.network.model.RepositoryResponse
import escola.c6.challenge.network.model.RepositoryWrapperResponse

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<RepositoryResponse>>()
    val repositories: LiveData<List<RepositoryResponse>>
        get() = _repositories

    fun fetchRepositories() {
        RetrofitConfig.api.fetchRepositories().doRequest(
            tag = "abc",
            clazz = RepositoryWrapperResponse::class.java,
            onSuccess = {
                _repositories.postValue(it.items)
            }
        )
    }
}