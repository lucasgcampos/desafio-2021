package escola.c6.challenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import escola.c6.challenge.database.dao.RepositoryDao
import escola.c6.challenge.database.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class ChallengeDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: ChallengeDatabase? = null

        fun getDatabase(context: Context): ChallengeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context.applicationContext,
                        ChallengeDatabase::class.java,
                        "challenge_database"
                    )
                    .allowMainThreadQueries() // AVOID THIS CODE IN PRODUCTION PROJECT
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}