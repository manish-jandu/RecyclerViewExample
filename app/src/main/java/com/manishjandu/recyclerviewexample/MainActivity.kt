package com.manishjandu.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.security.auth.login.LoginException
import kotlin.random.Random

class MainActivity : AppCompatActivity() ,ExampleAdapter.OnItemClickListener{
    companion object{
        const val TAG = "MainActivity"
    }

    private val exampleList:ArrayList<ExampleItem> = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.adapter = adapter
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

    }

    fun insertItem(view:View){
        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.ic_android,
            "New item at position $index",
            "Line 2"
        )
        exampleList.add(index,newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view:View){
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked" , Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.title = "clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list.add(item)
        }
        return list
    }
}