package com.nishanth.simplecomposeui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    private val _sheetType = mutableStateOf(SheetType.NONE)
    val sheetType: State<SheetType> = _sheetType

    fun showSheet(type: SheetType) {
        _sheetType.value = type
    }

    fun hideSheet() {
        _sheetType.value = SheetType.NONE
    }
}
