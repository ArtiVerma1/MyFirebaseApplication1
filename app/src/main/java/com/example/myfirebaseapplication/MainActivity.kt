package com.example.myfirebaseapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue


lateinit var txtname:TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         txtname = findViewById<TextView>(R.id.txtname)
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

        val db = Firebase.database
        val myRef = db.getReference("UserName")
        //Log.d("TAG", "DocumentSnapshot data: ${myRef.get().}")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d("TAG", "Value is: $value")
                txtname.setText(value)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

//        val docRef = db.collection("User").document("zb6XbRq7EWwTmgbeDhXI")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d("TAG", "DocumentSnapshot data: ${document.data?.values}")
//                    txtname.setText(document.data?.values.toString())
//                } else {
//                    Log.d("TAG", "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("TAG", "get failed with ", exception)
//            }
   }
    fun GetSecondarydatabase(context: Context): FirebaseFirestore? {
        //  FirebaseApp app = FirebaseApp.initializeApp(context);
        var app: FirebaseApp? = FirebaseApp.initializeApp(context)
        val database: FirebaseFirestore
        if (app == null) {
            val options = FirebaseOptions.Builder().setProjectId("checkkotlinproject")
                .setApplicationId("contactcentral-61fa8")
                .setApiKey("AIzaSyBH1SaHZ0sv-7NvUl9QRWk7eyciYBKjyUc")
                .setDatabaseUrl("https://checkkotlinproject-default-rtdb.firebaseio.com")
                .setStorageBucket("checkkotlinproject.appspot.com").build()
            app = FirebaseApp.initializeApp(context, options, "ContactFirebasePlugin")
            database = FirebaseFirestore.getInstance(app)
        } else {
            database = FirebaseFirestore.getInstance(app)
        }
        return database
    }
}