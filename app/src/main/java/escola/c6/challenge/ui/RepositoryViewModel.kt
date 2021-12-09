package escola.c6.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import escola.c6.challenge.extension.isEmpty
import escola.c6.challenge.network.RetrofitConfig
import escola.c6.challenge.network.model.RepositoryResponse
import escola.c6.challenge.network.model.RepositoryWrapperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<RepositoryResponse>>()
    val repositories: LiveData<List<RepositoryResponse>>
        get() = _repositories

    fun fetchRepositories() {
        if (_repositories.isEmpty()) {
            RetrofitConfig.api.fetchRepositories().enqueue(object :
                Callback<RepositoryWrapperResponse> {
                override fun onResponse(
                    call: Call<RepositoryWrapperResponse>,
                    response: Response<RepositoryWrapperResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.items?.let {
                            _repositories.postValue(it)
                        }
                    }
                }

                override fun onFailure(call: Call<RepositoryWrapperResponse>, t: Throwable) {
                    // TODO: tratar falhas
                }
            })
        }
    }
}