package escola.c6.challenge.network

import escola.c6.challenge.network.model.RepositoryWrapperResponse
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun fetchRepositories() : Call<RepositoryWrapperResponse>
}