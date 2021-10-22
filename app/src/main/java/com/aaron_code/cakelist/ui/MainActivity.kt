package com.aaron_code.cakelist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.aaron_code.cakelist.MyApplication
import com.aaron_code.cakelist.R
import com.aaron_code.cakelist.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    //TODO: Replace imported generic RecyclerView Binding Adapter with own coded implementation

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        com.aaron_code.cakelist.MyApplication.currentContext = WeakReference(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel?.refresh()
        return true
    }
}
