package com.example.data.database

import androidx.room.*
import com.example.data.model.BookmarkedCondition
import com.example.data.model.SymptomCheckHistory
import com.example.data.model.TrackedMedication
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicalDao {

    // --- Bookmarks queries ---
    @Query("SELECT * FROM bookmarked_conditions ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkedCondition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkedCondition)

    @Query("DELETE FROM bookmarked_conditions WHERE id = :id")
    suspend fun deleteBookmarkById(id: String)

    @Query("SELECT * FROM bookmarked_conditions WHERE id = :id LIMIT 1")
    suspend fun getBookmarkById(id: String): BookmarkedCondition?


    // --- Symptom Checker History queries ---
    @Query("SELECT * FROM symptom_check_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<SymptomCheckHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: SymptomCheckHistory)

    @Query("DELETE FROM symptom_check_history WHERE id = :id")
    suspend fun deleteHistoryById(id: Int)

    @Query("DELETE FROM symptom_check_history")
    suspend fun clearAllHistory()


    // --- Tracked Medications queries ---
    @Query("SELECT * FROM tracked_medications ORDER BY timestamp DESC")
    fun getAllTrackedMedications(): Flow<List<TrackedMedication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedMedication(medication: TrackedMedication)

    @Query("DELETE FROM tracked_medications WHERE id = :id")
    suspend fun deleteTrackedMedicationById(id: Int)

    @Query("SELECT * FROM tracked_medications WHERE id = :id LIMIT 1")
    suspend fun getTrackedMedicationById(id: Int): TrackedMedication?
}
