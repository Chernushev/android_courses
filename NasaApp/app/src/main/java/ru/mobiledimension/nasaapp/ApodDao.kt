package ru.mobiledimension.nasaapp

import androidx.room.*

@Dao
interface ApodDao {
    @Query("SELECT * FROM APOD")
    fun getAll(): List<APOD>

    @Query("SELECT * FROM APOD WHERE date = :date")
    fun getByDate(date: String): APOD?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apod: APOD)

    @Update
    fun update(apod: APOD)

    @Delete
    fun delete(apod: APOD)
}