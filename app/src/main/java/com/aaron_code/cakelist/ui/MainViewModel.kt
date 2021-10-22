package com.aaron_code.cakelist.ui

import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.aaron_code.cakelist.R

import com.aaron_code.cakelist.interfaces.ICakeItemListener
import com.aaron_code.cakelist.interfaces.ICakeService
import com.aaron_code.cakelist.interfaces.IDialogService
import com.aaron_code.cakelist.model.Cake
import com.aaron_code.cakelist.mvvm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject
import com.aaron_code.cakelist.BR

class MainViewModel @Inject constructor(
    private val cakeService: ICakeService,
    private val dialogService: IDialogService,
    listener: ICakeItemListener
): BaseViewModel() {

    val itemBinding: ItemBinding<Cake> = ItemBinding.of(BR.viewModel, R.layout.cake_item)

    init {
        itemBinding.bindExtra(BR.listener, listener)
        populateCakes()
    }

    @Bindable
    var cakes: ObservableList<Cake> = ObservableArrayList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.cakes)
        }

    @Bindable
    var inProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.inProgress)
        }

    fun refresh() {
        populateCakes()
    }

    @Bindable
    var atLeastOneCake: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.atLeastOneCake)
        }

    private fun populateCakes() {
        inProgress = true
        GlobalScope.launch {
            try {
                val retrievedCakes = cakeService.getCakesAsync().await()
                GlobalScope.launch(context = Dispatchers.Main) {
                    inProgress = false
                    atLeastOneCake = retrievedCakes.count() > 0
                    cakes.clear()
                    cakes.addAll(retrievedCakes)
                }
            } catch (e: Exception) {
                GlobalScope.launch(context = Dispatchers.Main) {
                    dialogService.displayAlert("Server Error", "Unable to retrieve cakes. Please try again.")
                    inProgress = false
                }
            }
        }
    }
}