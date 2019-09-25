package net.marksheehan.doomtowndeckbuilder.utilities

import android.content.Context
import net.marksheehan.doomtowndeckbuilder.database.MainDatabase
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModelFactory
import net.marksheehan.doomtowndeckbuilder.viewmodels.PackChooserViewModelFactory
import net.marksheehan.doomtowndeckbuilder.viewmodels.RetrievePackDataRepository

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPackDataRepository(context: Context): RetrievePackDataRepository {
        return RetrievePackDataRepository.getInstance(
                MainDatabase.getInstance(context.applicationContext).cardEntityDao())
    }

    fun provideCardViewerViewModel(context: Context ): CardViewerViewModelFactory {
        val repository = getPackDataRepository(context)
        return CardViewerViewModelFactory(repository)
    }

    fun providePackChooserViewModel(context: Context ): PackChooserViewModelFactory {
        val repository = getPackDataRepository(context)
        return PackChooserViewModelFactory(repository)
    }
}
