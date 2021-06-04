package com.mgk.melih_rickmorty.data
import androidx.paging.PageKeyedDataSource
import com.mgk.melih_rickmorty.data.RmRepository
import com.mgk.melih_rickmorty.model.CharacterSingle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersPagingDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: RmRepository
) : PageKeyedDataSource<Int, CharacterSingle>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterSingle>
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersPage(1)

            callback.onResult(page.results, null, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterSingle>
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersPage(params.key)

            callback.onResult(page.results, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterSingle>
    ) {
        // Nothing to do
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}