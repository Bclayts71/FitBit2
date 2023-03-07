package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class SummaryFragment : Fragment() {
    private var items = mutableListOf<DisplayArticle>()
    private lateinit var db: AppDatabase
    private var result : Int = 0

    /*
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            // Call the new method within onViewCreated

            db = AppDatabase.getInstance(requireContext())
            lifecycleScope.launch {
                db.articleDao().getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        DisplayArticle(
                            entity.link,
                            entity.price,
                            entity.itemName
                        )
                    }.also { mappedList ->
                        items.clear()
                        items.addAll(mappedList)
                    }
                }
            }
        }

     */
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setFragmentResultListener("requestKey") { requestKey, bundle ->
        result = bundle.getInt("bundleKey")
    }
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        val text1 = view.findViewById<TextView>(R.id.textView4)
        val text2 = view.findViewById<TextView>(R.id.textView5)
        val text3 = view.findViewById<TextView>(R.id.textView6)

        println(items.sumOf { it.itemName!!.toInt() })
        println(items.size)
        //val size = items.sumOf { it.itemName!!.toInt() }
        //val total = items.size
        val size = 4
        val total = 8
        val average = 2

        text1.text = "Total Workouts: " + size.toString()
        text2.text = "Total Hours: " + total.toString()
        text3.text = "Average Workout: " + average.toString()
        return view
    }

}
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        val text1 = view.findViewById<TextView>(R.id.textView4)
        val text2= view.findViewById<TextView>(R.id.textView5)
        val text3 = view.findViewById<TextView>(R.id.textView6)

        //size = arguments?.getInt("arraySize")
        //total = arguments?.getInt("totalHours")
        db = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayArticle(
                        entity.link,
                        entity.price,
                        entity.itemName
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                }
            }
        }
        println(items.sumOf { it.itemName!!.toInt() })
        println(items.size)
        val size = items.sumOf { it.itemName!!.toInt() }
        val total = items.size
        text1.text = size.toString()
        text2.text = total.toString()
        //text3.text = average.toString()



        return view
    }
}

     */