package net.marksheehan.doomtowndeckbuilder.utilities

import android.content.Context
import com.google.samples.apps.sunflower.data.RetrievePackDataRepository
import net.marksheehan.doomtowndeckbuilder.database.MainDatabase
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPackDataRepository(context: Context): RetrievePackDataRepository {
        return RetrievePackDataRepository.getInstance(
                MainDatabase.getInstance(context.applicationContext).cardEntityDao())
    }

    fun providePackRepository(context: Context ): CardViewerViewModelFactory {
        val repository = getPackDataRepository(context)
        return CardViewerViewModelFactory(repository)
    }
}
