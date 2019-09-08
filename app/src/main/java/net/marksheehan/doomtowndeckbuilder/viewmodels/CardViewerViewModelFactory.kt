package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.samples.apps.sunflower.data.RetrievePackDataRepository

/**
 * Factory for creating a [CardViewerViewModel] with a constructor that takes a
 * [RetrievePackDataRepository].
 */
class CardViewerViewModelFactory(
        private val repository: RetrievePackDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardViewerViewModel(repository) as T
    }
}