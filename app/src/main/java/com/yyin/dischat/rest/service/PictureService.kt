package com.yyin.dischat.rest.service

import android.net.Uri
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.yyin.dischat.BuildConfig
import com.yyin.dischat.rest.dto.ApiUploadToken
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream
import java.net.URI

interface PictureService{
    suspend fun uriToFile(uri: Uri):File?
    suspend fun uploadFile(file: File, filename: String)
    suspend  fun uploadFileStream(inputStream: InputStream, filename: String)
}

class PictureServiceImpl(
    private val client: HttpClient,
    private val uploaderManager: UploadManager,
    private val upCompletionHandler: UpCompletionHandler
) : PictureService {

     private suspend fun getUploadToken(): String{
         try{
             val url = getUploadTokenUrl()
             val api: ApiUploadToken =  withContext(Dispatchers.IO){
                 client.get(url).body()
             }
             if (api.token == null){
                 throw Exception("upload token is null")
             }else{
                 return api.token
             }
         }catch (e: Exception){
             throw e
         }
    }

    override suspend fun uriToFile(uri: Uri) :File?{
        return  File(URI(uri.toString()))
    }


    override suspend fun uploadFile(file: File, filename: String) {
        uploaderManager.put(file, filename, getUploadToken(), upCompletionHandler, null)
    }

    override suspend fun uploadFileStream(inputStream: InputStream, filename: String){
        uploaderManager.put(inputStream,null,-1,filename,filename, getUploadToken(), upCompletionHandler, null)
    }


    private companion object{
        const val BASE = BuildConfig.URL_API

        fun getUploadTokenUrl(): String {
            return "${BASE}/uploadToken"
        }
    }
}