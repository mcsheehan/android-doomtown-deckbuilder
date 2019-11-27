package net.marksheehan.doomtowndeckbuilder

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import okhttp3.OkHttpClient
import okhttp3.Request
import org.robolectric.annotation.Config
import java.io.File


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
public class DownloadAllImagesTest{

    fun downloadSingleImage(downloadUrl : String, saveFilePath : String){
        val client = OkHttpClient()


        val request = Request.Builder().url(downloadUrl)
                .addHeader("Content-Type", "application/json")
                .build()

        val response = client.newCall(request).execute()
        val inputStream = response.body()!!.byteStream()

        val myfile = File(saveFilePath)

        val outputStream = myfile.outputStream()

        inputStream.copyTo(outputStream)
        outputStream.flush()
        response.body()?.close()
    }

    @Test
    fun downloadOneFile(){
        val fullDownloadUrl = "https://dtdb.co/web/bundles/dtdbcards/images/cards/en/21052.jpg"
        val imageFileName : String = fullDownloadUrl.substringAfterLast("/")

        val saveFilePath : String = "C:\\Code\\android-doomtown-deckbuilder\\allCards\\" + imageFileName
        downloadSingleImage(fullDownloadUrl, saveFilePath)
    }

    @Test
    fun parseJsonFromGsonThenGetImages() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val cardList = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context, "card_list.json")

        //There are more than 800 cards and this value can only increase.
        Assert.assertTrue(cardList.size > 800)

        cardList.forEach {
            val downloadUrl : String = "https://dtdb.co" + it.imagesrc
            val imageFileName : String = downloadUrl.substringAfterLast("/")

            val saveFilePath : String = "C:\\Code\\android-doomtown-deckbuilder\\allCards\\" + imageFileName
//            downloadSingleImage(downloadUrl, saveFilePath)
        }

        //Assert that files in directory equals number of cards.
        //Assert that all files > 0 bytes

    }

}