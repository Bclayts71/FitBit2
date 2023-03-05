package com.example.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.Serializable

class InputDisplay : AppCompatActivity() {
    //private var items = mutableListOf<DisplayArticle>()
    private lateinit var submitButton: Button
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_display)
        submitButton = findViewById(R.id.button3)
        editText1 = findViewById(R.id.text1)
        editText2 = findViewById(R.id.text2)
        editText3 = findViewById(R.id.text3)
        submitButton.setOnClickListener {
            val text1 = editText1.text.toString().uppercase()
            val text2 = editText2.text.toString().uppercase()
            val text3 = editText3.text.toString().uppercase()
            val arr = arrayOf(text2, text3, text1)
            //val item = DisplayArticle(text2, text3, text1)
            //items.add(item)
            /*
            lifecycleScope.launch(IO) {
                (application as ArticleApplication).db.articleDao().deleteAll()
                (application as ArticleApplication).db.articleDao().insertAll(items.map {
                    ArticleEntity(
                        link = it.link,
                        price = it.price,
                        itemName = it.itemName
                    )
                })
            }

             */
            //  Navigate to Details screen and pass selected article
            val intent2 = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("test2", arr)
            startActivity(intent2)
        }
    }
}
