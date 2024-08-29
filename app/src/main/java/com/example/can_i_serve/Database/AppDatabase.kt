package com.example.can_i_serve.Database
// AppDatabase.kt
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.can_i_serve.Database.Dao.CanIServeDao
import com.example.can_i_serve.Database.DataClass.RegisterDetailsDb

@Database(entities = [RegisterDetailsDb ::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun canIServeDao(): CanIServeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
