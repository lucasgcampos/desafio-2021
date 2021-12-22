package escola.c6.challenge.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import escola.c6.challenge.database.ChallengeDao
import escola.c6.challenge.database.model.RepositoryEntity

@Dao
abstract class RepositoryDao : ChallengeDao<RepositoryEntity> {

    @Query("SELECT * from repository")
    abstract fun find(): List<RepositoryEntity>

    @Insert
    abstract fun save(repository: List<RepositoryEntity>?)

    @Delete(entity = RepositoryEntity::class)
    abstract fun delete(repositoryName: RepositoryEntity)
}