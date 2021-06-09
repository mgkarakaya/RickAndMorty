
package com.mgk.melih_rickmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mgk.melih_rickmorty.api.RmRetrofitService
import com.mgk.melih_rickmorty.model.CharacterSingle

private const val STARTING_PAGE_INDEX = 1

class PagingSource(
    private val service: RmRetrofitService
) : PagingSource<Int, CharacterSingle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterSingle> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getCharactersPage(page)
            val characters = response.results
            LoadResult.Page(
                data = characters,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.info.pages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterSingle>): Int? {
        TODO("Not yet implemented")
    }
}
