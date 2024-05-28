@file:Suppress("UNCHECKED_CAST")

package dev.xget.havasreddit.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl

fun <Vm : ViewModel> viewModelFactory(initializer : () -> Vm) : ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}