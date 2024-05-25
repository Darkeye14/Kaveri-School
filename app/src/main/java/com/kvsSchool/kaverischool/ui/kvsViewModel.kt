package com.kvsSchool.kaverischool.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kvsSchool.kaverischool.States.errorMsg
import com.kvsSchool.kaverischool.States.inProgress
import com.kvsSchool.kaverischool.States.onError
import com.kvsSchool.kaverischool.States.signIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class kvsViewModel @Inject constructor(
    val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
):ViewModel() {


    fun signUp1(
        name: String,
        email: String,
        password: String,
        section: String,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch {
        inProgress.value = true
        if (email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please Fill All The Fields")
        }

        auth.createUserWithEmailAndPassword(email, password)

            .addOnFailureListener {
                handleException(it)
            }
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    createAccount(name, email, password,section)
                    signIn.value = true
                    inProgress.value = false
    //                navigateTo(navController, DestinationScreen.HomeScreen.route)
                } else {
                    handleException(customMessage = " SignUp error")
                }
            }
    }

    fun createAccount(
        name: String,
        email: String,
        pwd: String,
        section :String
    ) = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        val acc = com.kvsSchool.kaverischool.data.Account(
            name = name,
            emailId = email,
            password = pwd,
            section = section,
            authId = auth.currentUser?.uid
        )
//        db.collection(ACCOUNTS)
//            .document()
//            .set(acc)

    }

    fun handleException(
        exception: Exception? = null,
        customMessage: String = ""
    ) {
        onError.value = true
        if (exception != null) {
                errorMsg.value = exception.toString()
            }
        else{
            errorMsg.value = customMessage
        }

        errorMsg.value = exception.toString()
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage
        val message =
            if (customMessage.isEmpty())
                errorMsg
            else
                customMessage
        onError.value = false
        inProgress.value = false
    }









}