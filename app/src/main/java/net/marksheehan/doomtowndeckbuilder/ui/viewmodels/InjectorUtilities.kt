package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import android.content.Context
import net.marksheehan.doomtowndeckbuilder.database.MainDatabase

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtilities {

    private fun getPackDataRepository(context: Context): RetrievePackDataRepository {
        return RetrievePackDataRepository.getInstance(
                MainDatabase.getInstance(context.applicationContext).cardEntityDao())
    }

    fun provideDeckViewerViewModel(context: Context ): BuildDeckViewModelFactory {
        val repository = getPackDataRepository(context)
        return BuildDeckViewModelFactory(repository)
    }


    fun provideCardViewerViewModel(context: Context ): CardViewerViewModelFactory {
        val repository = getPackDataRepository(context)
        return CardViewerViewModelFactory(repository)
    }

    fun providePackChooserViewModel(context: Context ): ChoosePacksViewModelFactory {
        val repository = getPackDataRepository(context)
        return ChoosePacksViewModelFactory(repository)
    }

    fun provideChooseIdentityViewModel(context: Context) : ChooseIdentityViewModelFactory{
        val repository = getPackDataRepository(context)
        return ChooseIdentityViewModelFactory(repository)
    }

    fun provideViewDecksViewModel(context: Context) : ViewDecksViewModelFactory{
        val repository = getPackDataRepository(context)
        return ViewDecksViewModelFactory(repository)
    }

}
