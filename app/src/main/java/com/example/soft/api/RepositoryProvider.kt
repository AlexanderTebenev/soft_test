package remote.retrofit

import android.content.Context

object RepositoryProvider {
    fun getRepository(context: Context): Repository {
        return Repository(ApiService.create(context))
    }
}