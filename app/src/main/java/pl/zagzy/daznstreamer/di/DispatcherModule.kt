package pl.zagzy.daznstreamer.di

import android.content.ContentResolver
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import pl.zagzy.daznstreamer.data.remote.DaznRemoteRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main


    @Provides
    fun providesContentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver

    @Provides
    fun providesRemoteRepository(): DaznRemoteRepository =
        Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DaznRemoteRepository::class.java)
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
