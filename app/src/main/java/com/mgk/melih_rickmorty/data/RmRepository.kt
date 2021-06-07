package com.mgk.melih_rickmorty.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mgk.melih_rickmorty.api.RmRetrofitService
import com.mgk.melih_rickmorty.model.CharacterList
import com.mgk.melih_rickmorty.model.CharacterSingle
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class RmRepository @Inject constructor(private val retrofitService: RmRetrofitService) {

    suspend fun getCharactersPage(pageIndex: Int): CharacterList {
        return retrofitService.getCharactersPage(pageIndex)
    }
    fun getSearchResultStream(): Flow<PagingData<CharacterSingle>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 25),
            pagingSourceFactory = { PagingSource(retrofitService) }
        ).flow
    }
}
