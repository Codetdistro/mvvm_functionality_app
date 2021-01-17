package com.example.movieapp

import net.simplifiedcoding.data.network.ApiInteface

class Repository(val api:ApiInteface) {

    suspend fun getRepositoryResult() = api.getData()
}