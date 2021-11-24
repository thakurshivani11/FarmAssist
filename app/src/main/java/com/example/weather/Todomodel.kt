package com.example.weather

class Todomodel {
    companion object Factory{
        fun createList(): Todomodel=Todomodel()
    }
    var UID:String?=null
    var itemDataText:String?=null
    var done: Boolean?= false
}