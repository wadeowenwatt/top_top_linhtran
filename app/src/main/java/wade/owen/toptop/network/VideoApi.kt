package wade.owen.toptop.network

import retrofit2.http.GET
import retrofit2.http.Header
import wade.owen.toptop.data.model.ApiListVideoResponse

interface VideoApi {
    @GET("/videos/popular")
    suspend fun getListVideo(@Header("Authorization") accessToken: String): ApiListVideoResponse?
}