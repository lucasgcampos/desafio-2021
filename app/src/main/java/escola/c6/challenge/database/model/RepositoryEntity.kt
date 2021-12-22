package escola.c6.challenge.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import escola.c6.challenge.network.model.RepositoryResponse

@Entity(tableName = "repository")
class RepositoryEntity(
    @PrimaryKey val name: String,
    val description: String,
    val forks: Int,
    val stars: Int,
    val login: String,
    val avatar: String,
) {
    constructor(response: RepositoryResponse) : this(
        name = response.name,
        description = response.description,
        forks = response.forks,
        stars = response.stars,
        login = response.owner.login,
        avatar = response.owner.avatar,
    )
}
