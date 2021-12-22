package escola.c6.challenge.ui.model

import escola.c6.challenge.database.model.RepositoryEntity
import escola.c6.challenge.network.model.OwnerResponse
import escola.c6.challenge.network.model.RepositoryResponse

class Repository(
    val name: String,
    val description: String,
    val forks: Int,
    val stars: Int,
    val owner: RepositoryOwner
) {
    constructor(response: RepositoryResponse) : this(
        name = response.name,
        description = response.description,
        forks = response.forks,
        stars = response.stars,
        owner = RepositoryOwner(response.owner)
    )

    constructor(response: RepositoryEntity) : this(
        name = response.name,
        description = response.description,
        forks = response.forks,
        stars = response.stars,
        owner = RepositoryOwner(
            login = response.login,
            avatar = response.avatar
        )
    )
}

class RepositoryOwner(
    val login: String,
    val avatar: String,
) {
    constructor(response: OwnerResponse) : this(
        login = response.login,
        avatar = response.avatar
    )
}