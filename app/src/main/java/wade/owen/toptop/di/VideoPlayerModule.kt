package wade.owen.toptop.di

import android.app.Application
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object VideoPlayerModule {

    @OptIn(UnstableApi::class) @Provides
    @ViewModelScoped
    fun provideExoPlayer(app: Application): ExoPlayer {
        return ExoPlayer.Builder(app).build()
    }
}