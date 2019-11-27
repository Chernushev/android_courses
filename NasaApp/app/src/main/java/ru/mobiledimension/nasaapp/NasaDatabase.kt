package ru.mobiledimension.nasaapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [APOD::class], version = 2)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE APOD ADD COLUMN id INTEGER")
    }
}

