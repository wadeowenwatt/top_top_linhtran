package wade.owen.toptop.di

import com.squareup.moshi.Moshi
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import wade.owen.toptop.VideoApi
import javax.inject.Singleton

object AppModule {
    private const val BASE_URL = "https://api.pexels.com"

    @Provides
    @Singleton
    fun provideMovieApi(moshi : Moshi) : VideoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(VideoApi::class.java)
    }
}