package com.example.phonepeandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.phonepeandroid.R
import com.example.phonepeandroid.repository.modal.OptionContent
import com.example.phonepeandroid.view.UiState.ERROR
import com.example.phonepeandroid.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpObservers()
        viewmodel.fetchOptions()
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewmodel.uiStateLiveData.observe(this@MainActivity, Observer {
                when(it) {
                    is UiState.LOADING -> {
                        showLoadingUI()
                    }
                    is UiState.ERROR -> {
                        showErrorUi(it.errorMessage)
                    }
                    is UiState.SUCCESS -> {
                        showContentUi(it.item)
                    }
                }
            })
        }
    }

    private fun showContentUi(item: List<OptionContent>) {

    }

    private fun showErrorUi(errorMessage: String) {

    }

    private fun showLoadingUI() {
    }
}