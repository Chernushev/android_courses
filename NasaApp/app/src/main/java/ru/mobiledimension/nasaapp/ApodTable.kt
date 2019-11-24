package ru.mobiledimension.nasaapp

class ApodTable {
    companion object {
        const val tableName = "apod_table"

        const val date = "date"
        const val copyright = "copyright"
        const val explanation = "explanation"
        const val hdUrl = "hdurl"
        const val mediaType = "media_type"
        const val serviceVersion = "service_version"
        const val title = "title"
        const val url = "url"

        fun createSql(): String =
            """CREATE TABLE $tableName (
                    $date TEXT,
                    $explanation TEXT,
                    $hdUrl TEXT,
                    $mediaType TEXT,
                    $serviceVersion TEXT,
                    $title TEXT,
                    $url TEXT,
                    UNIQUE ($date)
                    ON CONFLICT REPLACE )"""
    }
}