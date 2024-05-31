package com.kvsSchool.kaverischool.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.kvsSchool.kaverischool.POSTS
import com.kvsSchool.kaverischool.States.errorMsg
import com.kvsSchool.kaverischool.States.imageUriList
import com.kvsSchool.kaverischool.States.inProgress
import com.kvsSchool.kaverischool.States.onError
import com.kvsSchool.kaverischool.States.postsDataList
import com.kvsSchool.kaverischool.States.signIn
import com.kvsSchool.kaverischool.data.recievingPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class kvsViewModel @Inject constructor(
    val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
):ViewModel() {

    init {
        populatePost()
    }

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
    fun populatePost()= CoroutineScope(Dispatchers.IO).launch  {
        inProgress.value = true
        db.collection(POSTS).orderBy("sortTime", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { value->
                if (value != null) {
                    postsDataList.value = value.documents.mapNotNull {
                        it.toObject<recievingPost>()
                    }
                    inProgress.value = false
                }
            }
            .addOnFailureListener{
                errorMsg.value = "Invalid User"
                onError.value = true
            }
    }
    fun downloadMultipleImages(
        uri: String?,
        uid : String,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val imageUri = mutableStateOf<Bitmap?>(null)

        inProgress.value = true
        try {
            val maxDownloadSize = 5L * 1024 * 1024
            val storageRef = FirebaseStorage.getInstance().reference
            val bytes = storageRef.child("images/$uid/$uri")
                .getBytes(maxDownloadSize)
                .await()
            imageUri.value = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            imageUriList.add(imageUri.value)


        } catch (e: Exception) {
            handleException(e)
        }
        inProgress.value = false
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