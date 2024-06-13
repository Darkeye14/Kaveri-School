package com.kvsSchool.kaverischool.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.kvsSchool.kaverischool.ALLPICS
import com.kvsSchool.kaverischool.ANNOUNCEMET
import com.kvsSchool.kaverischool.POSTS
import com.kvsSchool.kaverischool.STUDENTS
import com.kvsSchool.kaverischool.States.allImageUriList
import com.kvsSchool.kaverischool.States.announcementsDataList
import com.kvsSchool.kaverischool.States.errorMsg
import com.kvsSchool.kaverischool.States.imageUriList
import com.kvsSchool.kaverischool.States.inProgress
import com.kvsSchool.kaverischool.States.onError
import com.kvsSchool.kaverischool.States.postsDataList
import com.kvsSchool.kaverischool.data.Account
import com.kvsSchool.kaverischool.data.PicUid
import com.kvsSchool.kaverischool.data.Announcement
import com.kvsSchool.kaverischool.data.SignUpEvent
import com.kvsSchool.kaverischool.data.recievingPost
import com.kvsSchool.kaverischool.navigation.DestinationScreen
import com.kvsSchool.kaverischool.util.navigateTo
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
) : ViewModel() {
    var signIn = mutableStateOf(false)
    val eventMutableState = mutableStateOf<SignUpEvent<String?>?>(null)

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(STUDENTS)
            .document(uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    handleException(error, "cannot retrieve user")
                }
                if (value != null) {
                    val user = value.toObject<Account>()
                    populatePost()
                    onShowAllPics()
                    inProgress.value = false

                }
            }
        inProgress.value = false
    }

    fun login(
        email: String,
        password: String,
        navController: NavController

    ) = CoroutineScope(Dispatchers.IO).launch {
        inProgress.value = true



        if (email.isEmpty() || password.isEmpty()) {
            handleException(customMessage = "Please fill all the fields")

        } else {
            inProgress.value = true
            try {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnFailureListener {
                        handleException(it)
                    }
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            signIn.value = true
                            auth.currentUser?.uid?.let {
                                getUserData(it)
                            }
                            inProgress.value = false
                            afterLogin(navController)
                        } else {
                            handleException(it.exception, customMessage = "Login failed")
                        }

                    }
                    .await()
            } catch (e: FirebaseAuthException) {
                handleException(e)
            }
            inProgress.value = false

        }
    }

    private fun afterLogin(
        navController: NavController
    ) = CoroutineScope(Dispatchers.Main).launch {
        delay(500)
        navigateTo(navController, DestinationScreen.HomeScreen.route)
    }

    fun signUp1(
        studentName: String,
        parentsName: String,
        parentsNumber: String,
        standard: String,
        email: String,
        password: String,
        section: String,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch {
        inProgress.value = true

        auth.createUserWithEmailAndPassword(email, password)

            .addOnFailureListener {
                handleException(it)
            }
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    signIn.value = true
                    createAccount(
                        standard,
                        studentName,
                        parentsName,
                        parentsNumber,
                        email,
                        password,
                        section
                    )
                    inProgress.value = false
                    navigateTo(navController, DestinationScreen.HomeScreen.route)
                } else {
                    handleException(customMessage = " SignUp error")
                }
            }
    }

    private fun createAccount(
        standard: String,
        studentName: String,
        parentsName: String,
        parentsNumber: String,
        email: String,
        password: String,
        section: String,
    ) = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        val acc = Account(
            studentName = studentName,
            parentsName = parentsName,
            parentsNumber = parentsNumber,
            emailId = email,
            password = password,
            section = section,
            standard = standard,
            authId = auth.currentUser?.uid
        )
        db.collection(STUDENTS)
            .document()
            .set(acc)

    }

    fun populatePost() = CoroutineScope(Dispatchers.IO).launch {
        inProgress.value = true

        db.collection(POSTS).orderBy("sortTime", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { value ->
                if (value != null) {
                    postsDataList.value = value.documents.mapNotNull {
                        it.toObject<recievingPost>()
                    }
                    inProgress.value = false
                }
            }
            .addOnFailureListener {
                errorMsg.value = "Invalid User"
                onError.value = true
            }
        inProgress.value = true
    }


    fun populateAnnouncement(
        classNo: String,
        section: String = "A"
    ) = CoroutineScope(Dispatchers.IO).launch {


        inProgress.value = true
        db.collection(ANNOUNCEMET).document(classNo)
            .collection(section)
            .orderBy("sortTime", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { value ->
                if (value != null) {
                    announcementsDataList.value = value.documents.mapNotNull {
                        it.toObject<Announcement>()
                    }
                    inProgress.value = false
                }
            }
            .addOnFailureListener {
                errorMsg.value = "Invalid User"
                onError.value = true
            }
    }


    fun downloadMultipleImages(
        uid: String,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val imageUri = mutableStateOf<Bitmap?>(null)

        inProgress.value = true
        try {
            val maxDownloadSize = 5L * 1024 * 1024
            val storageRef = FirebaseStorage.getInstance().reference
            val bytes = storageRef.child("PostImages/$uid")
                .getBytes(maxDownloadSize)
                .await()
            imageUri.value = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            imageUriList.add(imageUri.value)


        } catch (e: Exception) {
            handleException(e)
        }
        inProgress.value = false
    }

    fun onShowAllPics() = CoroutineScope(Dispatchers.IO).launch {
        inProgress.value = true
        val snapShot = db.collection(ALLPICS)
            .get()
            .await()

        for (doc in snapShot.documents) {
            val post = doc.toObject<PicUid>()
            downloadAllImages(post!!.uid)

        }
        inProgress.value = false
    }

    fun downloadAllImages(uid: String?) = CoroutineScope(Dispatchers.IO).launch {
        val imageUri = mutableStateOf<Bitmap?>(null)
        inProgress.value = true

        try {
            val maxDownloadSize = 5L * 1024 * 1024
            val storageRef = FirebaseStorage.getInstance().reference

            val bytes = storageRef.child("AllImages/$uid")
                .getBytes(maxDownloadSize)
                .await()
            imageUri.value = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            allImageUriList.add(imageUri.value)


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
            eventMutableState.value = SignUpEvent(exception.toString())
        } else {
            errorMsg.value = customMessage
            eventMutableState.value = SignUpEvent(customMessage)
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