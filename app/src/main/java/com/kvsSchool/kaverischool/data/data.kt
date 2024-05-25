package com.kvsSchool.kaverischool.data

data class Account (
    val name : String,
    val emailId : String,
    val password : String,
    val section : String,
    val authId: String?
)