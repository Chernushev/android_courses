package ru.mobiledimension.nasaapp.data.db.room

import androidx.room.*

@Dao
interface ApodDao {
    @Query("SELECT * FROM APOD")
    fun getAll(): List<ApodEntity>

    @Query("SELECT * FROM APOD WHERE date = :date")
    fun getByDate(date: String): ApodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apod: ApodEntity)

    @Update
    fun update(apod: ApodEntity)

    @Delete
    fun delete(apod: ApodEntity)
}