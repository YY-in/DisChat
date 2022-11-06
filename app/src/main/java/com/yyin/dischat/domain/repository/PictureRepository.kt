package com.yyin.dischat.domain.repository

import android.net.Uri
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.rest.service.PictureService


interface PictureRepository {
    suspend fun uploadImg(uri: Uri,key:String,upCompletionHandler: UpCompletionHandler)
}

class PictureRepositoryImpl(
    private val service: PictureService,
    private val uploaderManager: UploadManager,
) : PictureRepository {

    override suspend fun uploadImg(uri: Uri,key:String,upCompletionHandler: UpCompletionHandler) {
        uploaderManager.put(uri,null,key,service.getUploadToken(), upCompletionHandler,null)
    }

}