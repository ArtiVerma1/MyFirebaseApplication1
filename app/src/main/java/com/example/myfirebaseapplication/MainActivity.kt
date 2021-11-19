package com.example.myfirebaseapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.FirebaseOptions




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                //Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
//          //  Log.d(TAG, msg)
//           // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//        })
       // FirebaseApp.initializeApp(this)
        GetSecondarydatabase(this)
        val db = Firebase.firestore
        val docRef = db.collection("User")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("TAG", "DocumentSnapshot data: ${document.documents}")
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
            }
   }
    fun GetSecondarydatabase(context: Context): FirebaseFirestore? {
        //  FirebaseApp app = FirebaseApp.initializeApp(context);
        var app: FirebaseApp? = FirebaseApp.initializeApp(context)
        val database: FirebaseFirestore
        if (app == null) {
            val options = FirebaseOptions.Builder().setProjectId("checkkotlinproject")
                .setApplicationId("contactcentral-61fa8")
                .setApiKey("AIzaSyBH1SaHZ0sv-7NvUl9QRWk7eyciYBKjyUc")
                .setDatabaseUrl("https://contactcentral-61fa8-default-rtdb.firebaseio.com")
                .setStorageBucket("checkkotlinproject.appspot.com").build()
            app = FirebaseApp.initializeApp(context, options, "ContactFirebasePlugin")
            database = FirebaseFirestore.getInstance(app)
        } else {
            database = FirebaseFirestore.getInstance(app)
        }
        return database
    }
}