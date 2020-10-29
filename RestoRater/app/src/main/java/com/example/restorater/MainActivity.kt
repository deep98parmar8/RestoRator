package com.example.restorater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener{

            if ((!TextUtils.isEmpty(nameEditText.text)) && (ratingSpinner.selectedItemPosition > 0)){
                //capture the inputs
                val restaurant = Restaurant()
                restaurant.name = nameEditText.text.toString().trim()
                restaurant.rating = ratingSpinner.selectedItem.toString().toInt()

                //connect and save firebase
                val db = FirebaseFirestore.getInstance().collection("restaurants")
                restaurant.id = db.document().id
                db.document(restaurant.id!!).set(restaurant)

                //show confirmation & clear inputs
                nameEditText.setText("")
                ratingSpinner.setSelection(0)
                Toast.makeText(this,"Restaurant Added", Toast.LENGTH_LONG).show()


            }
            else{
                Toast.makeText(this,"Incompete", Toast.LENGTH_LONG).show()
            }
        }

        listFab.setOnClickListener {
            finish()
        }
    }
}