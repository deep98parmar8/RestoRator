package com.example.restorater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_restaurant.*
import org.w3c.dom.Comment

class RestaurantActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance().collection("comments")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)


        //populate Heading textview w/restaurant name
        restaurantNameTextView.text = intent.getStringExtra("name")?.toString()

        saveCommentButton.setOnClickListener {
            //create new comment and save to firebase
            if ((!TextUtils.isEmpty(usernameEditText.text)) && (!TextUtils.isEmpty(bodyEditText.text))) {
            //get new id from Firebase
                val id = db.document().id
                val comment = Comment(id, usernameEditText.text, bodyEditText.text, intent.getStringArrayExtra("restaurantId"))
                db.document(id).set(comment)

                //clear values
                usernameEditText.setText("")
                bodyEditText.setText("")
                Toast.makeText(this, "Comment added", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Incomplete", Toast.LENGTH_LONG).show()
            }
        }
    }
}
