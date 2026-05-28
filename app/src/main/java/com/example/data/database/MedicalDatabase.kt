package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.BookmarkedCondition
import com.example.data.model.SymptomCheckHistory
import com.example.data.model.TrackedMedication

@Database(
    entities = [BookmarkedCondition::class, SymptomCheckHistory::class, TrackedMedication::class],
    version = 2,
    exportSchema = false
)
abstract class MedicalDatabase : RoomDatabase() {

    abstract fun medicalDao(): MedicalDao

    companion object {
        @Volatile
        private var INSTANCE: MedicalDatabase? = null

        fun getDatabase(context: Context): MedicalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicalDatabase::class.java,
                    "clinical_guide_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
