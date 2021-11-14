package escola.c6.challenge.network.model

import com.google.gson.annotations.SerializedName

class RepositoryWrapperResponse(
    @SerializedName("items") val items: List<RepositoryResponse>
)

class RepositoryResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks_count") val forks: Int,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("owner") var owner: OwnerResponse
)

class OwnerResponse(
    @SerializedName("login") val login: String,
    @SerializedName("description") val description: String,
    @SerializedName("avatar_url") val avatar: String,
)