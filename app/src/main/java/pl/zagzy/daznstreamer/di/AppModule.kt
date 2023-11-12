package pl.zagzy.daznstreamer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import pl.zagzy.daznstreamer.utils.AndroidTime
import pl.zagzy.daznstreamer.utils.CurrentTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    fun provideCurrentTime(): CurrentTime = AndroidTime()
}
