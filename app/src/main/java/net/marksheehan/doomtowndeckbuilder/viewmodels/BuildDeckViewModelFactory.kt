package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating a [BuildDeckViewModel] with a constructor that takes a
 * [RetrievePackDataRepository].
 */
class BuildDeckViewModelFactory(
        private val repository: RetrievePackDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BuildDeckViewModel(repository) as T
    }
}