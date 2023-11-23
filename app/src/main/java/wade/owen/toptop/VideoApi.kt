package wade.owen.toptop

import retrofit2.http.GET
import retrofit2.http.Header

interface VideoApi {
    @GET("/videos/popular")
    suspend fun getListVideo(@Header("Authorization") accessToken: String)
}