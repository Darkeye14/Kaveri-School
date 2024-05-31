package com.kvsSchool.kaverischool.data

data class Account (
    val name : String,
    val emailId : String,
    val password : String,
    val section : String,
    val authId: String?
)

data class recievingPost(
    val title :String? = "",
    val disc :String ? = "",
    val imageList : List<String?> ?= listOf(),
    val uid :String?= "",
    val sortTime : String?="",
    val timeStamp :String?="",
)
data class ToggleableInfo(
    val isChecked: Boolean,
    val text : String
)