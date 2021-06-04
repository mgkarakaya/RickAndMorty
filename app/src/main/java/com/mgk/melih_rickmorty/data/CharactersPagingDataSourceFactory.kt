package com.mgk.melih_rickmorty.data

import androidx.paging.DataSource
import com.mgk.melih_rickmorty.model.CharacterSingle
import kotlinx.coroutines.CoroutineScope

class CharactersPagingDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: RmRepository
): DataSource.Factory<Int, CharacterSingle>() {

    override fun create(): DataSource<Int, CharacterSingle> {
        return CharactersPagingDataSource(coroutineScope, repository)
    }

}