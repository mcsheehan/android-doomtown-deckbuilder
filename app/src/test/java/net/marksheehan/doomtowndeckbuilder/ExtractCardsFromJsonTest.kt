package net.marksheehan.doomtowndeckbuilder

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingMoshi
import org.junit.Assert.*
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ExtractCardsFromJsonTest {

    @Test
    fun parseJsonUsingGson() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val cardList = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context, "card_list.json")

        //There are more than 800 cards and this value can only increase.
        assertTrue(cardList.size > 800)
    }

    @Test
    fun parseJsonUsingMoshi() {

        val context = InstrumentationRegistry.getInstrumentation().context

        val cardList = ParseCardListFromJsonFileUsingMoshi.parseCardsFromAssetFile(context, "card_list.json")

        //There are more than 800 cards and this value can only increase.
        assertTrue(cardList.size > 800)
    }
}