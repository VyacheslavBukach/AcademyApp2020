package com.androidacademy.academyapp2020.utils

sealed class LoadStatus {
    object Loading : LoadStatus()
    object Success : LoadStatus()
    object Error : LoadStatus()
}