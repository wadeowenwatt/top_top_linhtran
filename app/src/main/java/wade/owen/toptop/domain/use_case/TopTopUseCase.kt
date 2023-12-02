package wade.owen.toptop.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import wade.owen.toptop.config.Resource
import wade.owen.toptop.data.model.ApiListVideoResponse
import wade.owen.toptop.data.repositories.VideoRepository
import javax.inject.Inject

class TopTopUseCase @Inject constructor(
    private val videoRepository: VideoRepository,
) {
    operator fun invoke(): Flow<Resource<ApiListVideoResponse?>> = flow {
        try {
            emit(Resource.Loading())
            val apiListVideoResponse = videoRepository.getListVideo()

            emit(Resource.Success(apiListVideoResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}