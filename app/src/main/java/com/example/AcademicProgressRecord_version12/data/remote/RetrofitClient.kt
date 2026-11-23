package com.example.AcademicProgressRecord_version12.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {
    private const val BASE_URL = "https://raw.githubusercontent.com/MarchenkoMaximOFKKT/grade-journal-api/main/"
    val api: SubjectApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SubjectApi::class.java)
    }
}