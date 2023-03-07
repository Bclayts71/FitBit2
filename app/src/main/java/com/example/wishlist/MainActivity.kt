package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity(),Communicator {
    //private lateinit var items: List<DisplayArticle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bestSellerBooksFragment: Fragment = DataFragment()
        val articleListFragment: Fragment = SummaryFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_books -> fragment = bestSellerBooksFragment
                R.id.nav_articles -> fragment = articleListFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_books
    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    override fun passData(dataPoint1: Int, dataPoint2: Int) {
        val bundle = Bundle()
        bundle.putInt("arraySize", dataPoint1)
        bundle.putInt("totalHours", dataPoint2)

        val transaction = this.supportFragmentManager.beginTransaction()
        val articleListFragment: Fragment = SummaryFragment()
        articleListFragment.arguments = bundle
        transaction.replace(R.id.article_frame_layout, articleListFragment).commit()
    }
}