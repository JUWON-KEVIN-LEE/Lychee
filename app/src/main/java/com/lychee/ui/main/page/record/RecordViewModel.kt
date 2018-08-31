package com.lychee.ui.main.page.record

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.lychee.data.core.model.Expenditure
import com.lychee.mock.MockData
import com.lychee.ui.base.BaseViewModel
import java.util.*

class RecordViewModel constructor() : BaseViewModel() {

    val month: MutableLiveData<Int> = MutableLiveData()

    val year: MutableLiveData<Int> = MutableLiveData()

    private val _date: MediatorLiveData<String> = MediatorLiveData()

    val date: LiveData<String> get() = _date

    private val _expenditures: MutableLiveData<List<Expenditure>> = MutableLiveData()

    val expenditures: LiveData<List<Expenditure>> get() = _expenditures

    init {
        val calendar = Calendar.getInstance()

        month.value = calendar.get(Calendar.MONTH) + 1
        year.value = calendar.get(Calendar.YEAR)

        _date.addSource(month) {
            _date.value = "${it}월 ${year.value}"
        }

        _date.addSource(year) {
            _date.value = "${month.value}월 $it"
        }

        _expenditures.value = mutableListOf()
    }

    fun fetchExpenditures() {
        _expenditures.value = MockData.get()
    }
}