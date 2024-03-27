package com.example.listapp

import android.content.Context
import com.example.listapp.data.room.ShoppingListDatabase
import com.example.listapp.ui.repository.Repository

object Graph {
    lateinit var db:ShoppingListDatabase
        private set

    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            storeDao = db.storeDao(),
            itemDao = db.itemDao()
        )
    }

    fun provide(context: Context){
        db = ShoppingListDatabase.getDatabase(context)
    }
}