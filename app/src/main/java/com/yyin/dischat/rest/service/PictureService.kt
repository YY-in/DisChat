package com.yyin.dischat.rest.service

import android.net.Uri
import android.util.Log
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
   suspend fun getUploadToken(): String
}

class PictureServiceImpl(
    private val client: HttpClient,
) : PictureService {

     override suspend fun getUploadToken(): String{
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



    private companion object{
        const val BASE = BuildConfig.URL_API

        fun getUploadTokenUrl(): String {
            return "${BASE}/uploadToken"
        }
    }
}