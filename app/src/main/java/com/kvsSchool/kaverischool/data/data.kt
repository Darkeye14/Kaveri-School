package com.kvsSchool.kaverischool.data

data class Account (
    val studentName : String,
    val parentsName: String,
    val parentsNumber: String,
    val standard: String,
    val emailId : String,
    val password : String,
    val section : String,
    val authId: String?
)

data class Announcement(
    val classroom : String ?="",
    val section : String?="",
    val text : String?="",
    val sortTime : String?="",
    val timeStamp :String?="",
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