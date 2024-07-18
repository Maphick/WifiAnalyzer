package com.makashovadev.wifianalyzer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException



// БД для хранения настроек
val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
by preferencesDataStore(name = "preferences")

class DataStoreRepository(context: Context) {
    private val dataStore = context.dataStore


    //  ключ, по которму сохраняем в БД флаг, пройден ли онбординг
    private object PreferencesKey {
        val displaySettingsKey = booleanPreferencesKey(name = "display_settings")
       // val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")

    }


    // сохранение в БД флага
    suspend fun saveDisplaySettingsState(displaySettingsOn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.displaySettingsKey] = displaySettingsOn
        }
    }

    // сохранение в БД флага, профдено ли приветствие
   /* suspend fun saveWelcomeState(welcomeCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.welcomeKey] = welcomeCompleted
        }
    }*/

    // чтение из БД флага
    fun readDisplaySettingsState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.displaySettingsKey] ?: false
                onBoardingState
            }
    }

    // чтение из БД флага, профдено ли приветствие
    /*fun readWelcomeState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val welcomeState = preferences[PreferencesKey.welcomeKey] ?: false
                welcomeState
            }
    }*/



    fun provideDataStoreRepository(context: Context): DataStoreRepository {
        return DataStoreRepository(context = context)
    }
}