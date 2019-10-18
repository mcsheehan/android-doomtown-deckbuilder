package net.marksheehan.doomtowndeckbuilder.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

/**
 * The Room database that contains the Users table
 */
@Database(entities = arrayOf(CardModel::class, DeckEntity::class, PackEntity::class), version = 2)

abstract class MainDatabase : RoomDatabase() {

    abstract fun cardEntityDao(): RetrievePackDao

    companion object {

        private const val dbName = "decks.db"

        @Volatile private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context): MainDatabase {

            return Room.databaseBuilder(context.applicationContext,
                    MainDatabase::class.java, dbName).fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<PopulateDatabase>().build()
                        WorkManager.getInstance(context).enqueue(request)
                }
            }).build()
        }
    }
}