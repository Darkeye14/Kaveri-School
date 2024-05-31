package com.kvsSchool.kaverischool.States

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.kvsSchool.kaverischool.data.recievingPost

var inProgress = mutableStateOf( false)
var onError = mutableStateOf( false)
var errorMsg = mutableStateOf( "")
var signIn = mutableStateOf(false)
var imageUriList = mutableStateListOf<Bitmap?>(null)
var singleImageUri = mutableStateOf<Bitmap?>(null)
var postsDataList = mutableStateOf<List<recievingPost>>(listOf())
