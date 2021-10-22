package com.aaron_code.cakelist.service

import android.app.AlertDialog
import android.content.Context
import com.aaron_code.cakelist.MyApplication
import com.aaron_code.cakelist.interfaces.IDialogService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

open class DialogService @Inject constructor(): IDialogService {

    companion object {
        private const val dismissIdentifier = "dismiss"
    }

    override fun displayAlert(title: String, message: String) {

        val context = com.aaron_code.cakelist.MyApplication.currentContext?.get()!!
        GlobalScope.launch(Dispatchers.Main) {
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getStringFromResourceName(context, dismissIdentifier)) { dialog, _ -> dialog.dismiss() }
            alertDialog.show()
        }
    }

    private fun getStringFromResourceName(context: Context, resourceName: String): String {
        val packageName = context.packageName
        val resourceId = context.resources.getIdentifier(resourceName, "string", packageName)
        return context.getString(resourceId)
    }
}