package com.makashovadev.wifianalyzer

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makashovadev.wifianalyzer.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormatSymbols
import java.util.Locale

class SettingsViewModel(
    private val repository: DataStoreRepository
): ViewModel() {

    private val _scanningIntervalPreference: MutableStateFlow<String> = MutableStateFlow("5")
    var scanningIntervalPreference = _scanningIntervalPreference.asStateFlow()

    private val _sortingPreference: MutableStateFlow<String> = MutableStateFlow("5")
    var sortingPreference = _sortingPreference.asStateFlow()

    private val _isSwitchOn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isSwitchOn = _isSwitchOn.asStateFlow()

    private val _isDisplaingSettings = MutableLiveData<Boolean>(false)
    var isDisplaingSettings : LiveData<Boolean> = _isDisplaingSettings


    private val _textPreference: MutableStateFlow<String> = MutableStateFlow("")
    var textPreference = _textPreference.asStateFlow()

    private val _intPreference: MutableStateFlow<Int> = MutableStateFlow(0)
    var intPreference = _intPreference.asStateFlow()


    init {
        viewModelScope.launch {
            // чтение натсроек из базы
            repository.readDisplaySettingsState().collect { displaySettingsState ->
                    _isDisplaingSettings.value = displaySettingsState
                }
            }
        }

    fun toggleSwitch(){
        _isSwitchOn.value = _isSwitchOn.value.not()
        // сохранить в память
    }

    //
    fun displaySettingsChange()
    {
        _isDisplaingSettings.value = _isDisplaingSettings.value?.not()
        // сохранение настроек в юазу
        _isDisplaingSettings.value?.let { saveDisplaySettingsState(it) }
    }

    // сохранение в базу флага, был ли пройден онбординг
    fun saveDisplaySettingsState(displaySettings: Boolean) {
        // запуск корутины в потоке ввода-вывода, чтобы распараллелить работу с базой
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveDisplaySettingsState(displaySettings)
        }
    }


    fun saveText(finalText: String) {
        _textPreference.value = finalText
        // сохранить в память
    }

    fun saveScanningInterval(finalInterval: String)
    {
        val value = finalInterval.toIntOrNull() ?: 0
        _scanningIntervalPreference.value = value.toString()
        // сохранить в память
    }


    fun checkTextInput(text: String) = text.isNotEmpty()




    // чтобы получить разделитель для локали
    private val separatorChar = DecimalFormatSymbols.getInstance(Locale.ENGLISH).decimalSeparator

    // фильтрация только чисел и десятичного разделителя
    fun filterNumbers(text: String): String = text.filter { it.isDigit() || it == separatorChar}

    // текст в число
    fun checkNumber(text: String): Boolean {
        val value = text.toDoubleOrNull() ?: return false
        return value < 0
    }

    // сохранение номера/показать ошибку
    fun saveNumber(text: String) {
        val value = text.toDoubleOrNull() ?: 0
    }

    companion object {
        const val TAG = "SettingsViewModel"
    }

}