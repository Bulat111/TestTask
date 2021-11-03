package com.example.usapopulation.utils

sealed class Resource<T> {
    class Success<T>(val fetchedData: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}