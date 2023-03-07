package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DataFragment : Fragment(){
    //private lateinit var db: AppDatabase
    private var items = mutableListOf<DisplayArticle>()
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        val wishRv = view.findViewById<RecyclerView>(R.id.wishRV)
        val editText1 = view.findViewById<EditText>(R.id.text1)
        val editText2 = view.findViewById<EditText>(R.id.text2)
        val editText3 = view.findViewById<EditText>(R.id.text3)
        val showButton = view.findViewById<Button>(R.id.button)
        //var items : MutableList<DisplayArticle> = ArrayList()


        val adapter = WishlistAdapter(this.items)
        wishRv.adapter = adapter
        /*
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "my-database"
        ).build()

         */
        //db = AppDatabase.getInstance(requireContext())
        lifecycleScope.launch {
            (requireActivity().application as ArticleApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayArticle(
                        entity.link,
                        entity.price,
                        entity.itemName
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        this.items = items
        // Create adapter passing in the list of emails

        // Attach the adapter to the RecyclerView to populate items

        // Set layout manager to position the items
        wishRv.layoutManager = LinearLayoutManager(context)

        showButton.setOnClickListener {
            val text1 = editText1.text.toString().uppercase()
            val text2 = editText2.text.toString().uppercase()
            val text3 = editText3.text.toString().uppercase()

            val item = DisplayArticle(text2, text3, text1)
            items.add(item)
            //communicator = activity as Communicator
            //communicator.passData(items.sumOf { it.itemName!!.toInt() }, items.size)
            println(items.sumOf { it.itemName!!.toInt() })
            println(items.size)
            val result = items.size
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            //println(items)
            lifecycleScope.launch(IO) {
                (requireActivity().application as ArticleApplication).db.articleDao().deleteAll()
                (requireActivity().application as ArticleApplication).db.articleDao().insertAll(items.map {
                    ArticleEntity(
                        link = it.link,
                        price = it.price,
                        itemName = it.itemName
                    )
                })
            }

        }
        return view
    }

}