package com.aaron_code.cakelist.ui

import com.aaron_code.cakelist.interfaces.ICakeItemListener
import com.aaron_code.cakelist.interfaces.IDialogService
import com.aaron_code.cakelist.model.Cake
import javax.inject.Inject

open class CakeItemListener @Inject constructor(
    private val dialogService: IDialogService
): ICakeItemListener {

    override fun onItemClick(cake: Cake) {
        dialogService.displayAlert("Description", cake.desc)
    }
}