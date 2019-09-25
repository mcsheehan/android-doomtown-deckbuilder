package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PackChooserViewModelFactory(
        private val repository: RetrievePackDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PackChooserViewModel(repository) as T
    }
}