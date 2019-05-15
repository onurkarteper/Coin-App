package com.onurkarteper.coinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurkarteper.coinapp.base.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val parentJob = Job()

    protected val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    protected val scope = CoroutineScope(coroutineContext)

    public val loading = MutableLiveData<Boolean>()
    public val errorMessage = SingleLiveEvent<String>()
}