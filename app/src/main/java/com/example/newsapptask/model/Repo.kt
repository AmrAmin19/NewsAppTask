package com.example.newsapptask.model

import com.example.newsapptask.model.remote.Iremote
import kotlinx.coroutines.flow.Flow

class Repo private constructor(
    var remoteData: Iremote
) : Irepo {

    companion object
    {
        private var instance : Repo? = null

        fun getInstance( remoteData : Iremote):Repo
        {
            return instance ?: synchronized(this)
            {
                val temp = Repo(remoteData)
                instance=temp
                temp
            }
        }
    }

    override fun getTopHeadlinesByCategory(category: String): Flow<ApiState<NewsResponse>> {
       return remoteData.getTopHeadlinesByCategory(category)
    }

}