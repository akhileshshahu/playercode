package com.akhiplayer.akhi.di


import android.content.Context
import com.akhiplayer.akhi.base.BaseActivity
import com.akhiplayer.akhi.playback.session.AkhiMediaService
import com.akhiplayer.akhi.ui.DashboardActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Application Dependency Graph
 */
@Singleton
@Component(
    modules = [
        AppModule::class,

        PlaybackModule::class,
        DataModule::class,

        //Media Categories
        AlbumModule::class,
        ArtistModule::class,
        PlaylistModule::class,
        TrackModule::class,
        GenreModule::class
    ]
)
internal interface AppComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(akhiMediaService: AkhiMediaService)

    fun inject(dashboardActivity: DashboardActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent

    }
}