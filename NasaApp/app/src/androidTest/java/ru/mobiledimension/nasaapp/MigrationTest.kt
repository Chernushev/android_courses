package ru.mobiledimension.nasaapp

import android.database.sqlite.SQLiteException
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.mobiledimension.nasaapp.data.db.room.MIGRATION_1_2
import ru.mobiledimension.nasaapp.data.db.room.NasaDatabase


@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @Rule
    @JvmField
    val testHelper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        NasaDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migationFrom1to2() {
        testHelper.createDatabase(TEST_DB, 1)
        testHelper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)
    }

    @Test(expected = SQLiteException::class)
    fun migationFrom1to2withDuplicate() {
        testHelper.createDatabase(TEST_DB, 1).apply {
            execSQL("ALTER TABLE APOD ADD COLUMN id INTEGER")
            close()
        }

        testHelper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)
    }
}