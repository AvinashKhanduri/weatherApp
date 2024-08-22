package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    fun provideOpenWeatherRepository(
        apiIneed: WeatherApi,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepository(api = apiIneed, weatherDao)
    }


    //Room Dependencies
    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).fallbackToDestructiveMigration().build()
    }

}