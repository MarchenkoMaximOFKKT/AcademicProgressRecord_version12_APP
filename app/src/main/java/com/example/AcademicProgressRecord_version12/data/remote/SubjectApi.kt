package com.example.AcademicProgressRecord_version12.data.remote

import retrofit2.http.GET
interface SubjectApi {
    @GET("subjects.json")
    suspend fun getSubjects(): List<SubjectDto>
    @GET("components.json")
    suspend fun getComponents(): List<ComponentDto>
}