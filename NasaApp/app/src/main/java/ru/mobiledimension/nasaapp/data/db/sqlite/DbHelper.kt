package ru.mobiledimension.nasaapp.data.db.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import ru.mobiledimension.nasaapp.domain.dto.APOD
import java.util.*

class DbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ApodTable.createSql())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun saveAPOD(apod: APOD) {
        writableDatabase.insert(
            ApodTable.tableName,
            null,
            contentValuesOf(
                ApodTable.date to apod.date,
                ApodTable.copyright to apod.copyright,
                ApodTable.explanation to apod.explanation,
                ApodTable.hdUrl to apod.hdurl,
                ApodTable.mediaType to apod.media_type,
                ApodTable.serviceVersion to apod.service_version,
                ApodTable.title to apod.title,
                ApodTable.url to apod.url)
        )
    }

    fun getAPOD(date: String): APOD? {
        var result: APOD? = null
        val query = """SELECT * FROM ${ApodTable.tableName}
                   WHERE ${ApodTable.date} = ?"""
        readableDatabase.rawQuery(query, arrayOf(date)).use { cursor ->
            cursor?.let {
                if (it.moveToFirst()) result = getAPODFromCursor(it)
            }
        }
        return result
    }

    private fun getAPODFromCursor(cursor: Cursor): APOD =
        APOD(
            copyright = cursor.getStringFromColumn(ApodTable.copyright),
            date = cursor.getStringFromColumn(ApodTable.date),
            explanation = cursor.getStringFromColumn(ApodTable.explanation),
            hdurl = cursor.getStringFromColumn(ApodTable.hdUrl),
            media_type = cursor.getStringFromColumn(ApodTable.mediaType),
            service_version = cursor.getStringFromColumn(ApodTable.serviceVersion),
            title = cursor.getStringFromColumn(ApodTable.copyright),
            url = cursor.getStringFromColumn(ApodTable.url),
            id = null // field for test migration
        )

    companion object {
        const val DATABASE_NAME = "NasaDB"
        const val DATABASE_VERSION = 1
        private val dbHelper: DbHelper? = null


        fun Cursor.getStringFromColumn(columnName: String?): String = this.getString(this.getColumnIndexOrThrow(columnName))
        fun Cursor.getBooleanFromColumn(columnName: String?): Boolean = getIntFromColumn(columnName) > 0
        fun Cursor.getLongFromColumn(columnName: String?): Long = this.getLong(this.getColumnIndexOrThrow(columnName))
        fun Cursor.getIntFromColumn(columnName: String?): Int = this.getInt(this.getColumnIndexOrThrow(columnName))
        fun Cursor.getFloatFromColumn(columnName: String?): Float = this.getFloat(this.getColumnIndexOrThrow(columnName))
        fun Cursor.getDoubleFromColumn(columnName: String?): Double = this.getDouble(this.getColumnIndexOrThrow(columnName))

        fun Collection<*>.makeSqlPlaceholders(): String
                = if(this.isEmpty()) "" else Collections.nCopies(this.size, "?").joinToString(separator = ", ")


        fun instance(context: Context) = dbHelper ?: DbHelper(context)
    }
}