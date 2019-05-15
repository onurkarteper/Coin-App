package com.onurkarteper.coinapp.base

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.onurkarteper.coinapp.R
import timber.log.Timber


@Suppress("UNUSED_ANONYMOUS_PARAMETER")
abstract class BaseActivity : AppCompatActivity() {


    public fun showBaseErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(
                R.string.txt_action_dialog_confirm
            ) { dialog, p1 -> dialog?.dismiss() }
            .show()
    }

    public fun closeKeyboard(view: View) {
        val inputManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}