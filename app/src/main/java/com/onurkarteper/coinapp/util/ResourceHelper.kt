package com.onurkarteper.coinapp.util

import android.content.Context
import javax.inject.Inject

class ResourceHelper @Inject constructor(
    private val context: Context
) {
    fun getString(resId: Int): String {
        return context.getString(resId)
    }
}