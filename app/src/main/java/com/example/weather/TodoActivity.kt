package com.example.weather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class TodoActivity : AppCompatActivity() ,UpdateandDelete{
    lateinit var database:DatabaseReference
    var toDoList:MutableList<Todomodel>?=null
    lateinit var adapter: TodoAdapter
    private var listViewItem:ListView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide(); // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_todo)

        val fab=findViewById<View>(R.id.fab) as FloatingActionButton

        listViewItem=findViewById(R.id.item_listView)
        database = FirebaseDatabase.getInstance().reference
        Log.d("database",database.toString())
        fab.setOnClickListener{
            val alertDialog=AlertDialog.Builder(this)
            val textEditText=EditText(this)
            alertDialog.setMessage("add todo item")
            alertDialog.setTitle("enter to do item")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("add"){
                dialog,i->
                val todoItemData=Todomodel.createList()
                todoItemData.itemDataText=textEditText.text.toString()
                todoItemData.done=false
                val newItemData=database.child("todo").push()
                todoItemData.UID=newItemData.key
                Log.d("newItemData",newItemData.toString())


                newItemData.setValue(todoItemData)
                dialog.dismiss()
                Toast.makeText(this,"item saved",Toast.LENGTH_LONG).show()

            }
            alertDialog.show()

        }
        toDoList= mutableListOf<Todomodel>()
        adapter= TodoAdapter(this,toDoList!!)
        listViewItem!!.adapter=adapter
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
            toDoList!!.clear()
                addItemToList(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(applicationContext,"no item added ",Toast.LENGTH_LONG).show()

            }

        })


    }

    private fun addItemToList(snapshot: DataSnapshot) {
        val items =snapshot.children.iterator()
        Log.d("items",items.hasNext().toString())

        if (items.hasNext())
        {
            val toDoIndexedValue=items.next()
            val itemsIterator=toDoIndexedValue.children.iterator()
            while (itemsIterator.hasNext())
            {
                val currentItem =itemsIterator.next()
                val toDoItemData=Todomodel.createList()
                val map = currentItem.getValue() as HashMap<String,Any>
                toDoItemData.UID=currentItem.key
                toDoItemData.done=map.get("done") as Boolean?
                toDoItemData.itemDataText=map.get("itemDataText") as String?
                //logcat uid
                Log.d("todoItem",toDoItemData.UID.toString())
                Log.d("todoItem2",toDoItemData.itemDataText.toString())


                toDoList!!.add(toDoItemData);
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
    val itemReference=database.child("todo").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val itemReference=database.child("todo").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()

    }
}
