package ru.mobiledimension.nasaapp.data.db.room

import androidx.room.*

@Dao
interface ApodDao {
    @Query("SELECT * FROM APOD")
    suspend fun getAll(): List<ApodEntity>

    @Query("SELECT * FROM APOD WHERE date = :date")
    suspend fun getByDate(date: String): ApodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apod: ApodEntity)

    @Update
    suspend fun update(apod: ApodEntity)

    @Delete
    suspend fun delete(apod: ApodEntity)
}