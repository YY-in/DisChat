package com.yyin.dischat.domain.repository

import com.yyin.dischat.rest.service.PictureService
import java.io.File
import java.io.InputStream

interface PictureRepository {
    suspend fun uploadFile(file: File, filename: String): String
    suspend  fun uploadFileStream(inputStream: InputStream, filename: String): String
    suspend  fun remove(key: String): String
}

class PictureRepositoryImpl(
    private val service: PictureService
) : PictureRepository {

    override suspend fun uploadFile(file: File, filename: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun uploadFileStream(inputStream: InputStream, filename: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun remove(key: String): String {
        TODO("Not yet implemented")
    }




}