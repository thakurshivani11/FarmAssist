package com.example.weather

interface UpdateandDelete {
    fun modifyItem(itemUID:String,isDone:Boolean)
    fun onItemDelete(itemUID: String)
}