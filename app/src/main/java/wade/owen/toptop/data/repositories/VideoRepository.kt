package wade.owen.toptop.data.repositories

import wade.owen.toptop.data.model.ApiListVideoResponse
import wade.owen.toptop.network.VideoApi
import javax.inject.Inject

class VideoRepository @Inject constructor(private var videoApi: VideoApi) {
    private val accessToken: String = "eVRzwlgwDZigZJWfY0JQCw9vYFDxFwxZbCAlrBRBjKXfTtMVlNZTOmXs";
    suspend fun getListVideo(): ApiListVideoResponse? {
        return videoApi.getListVideo(accessToken)
    }
}