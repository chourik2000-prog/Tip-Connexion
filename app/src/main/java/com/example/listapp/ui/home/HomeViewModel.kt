package com.example.listapp.ui.home

import android.icu.util.ULocale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapp.Graph
import com.example.listapp.data.room.ItemsWithStoreAndList
import com.example.listapp.data.room.models.Item
import com.example.listapp.ui.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale


class HomeViewModel(
    private val repository: Repository = Graph.repository
):ViewModel() {
    var state by mutableStateOf(HomeState())
    private set

    init{

    }
    private fun getItems(){
        viewModelScope.launch {
            repository.getItemsWithListAndStore.collectLatest {
                state = state.copy(
                    items = it
                )
            }
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun onCategoryChange(category: ULocale.Category){
        state = state.copy(category = category)
        filterBy(category.id)
    }

    fun onItemCheckedChange(item: Item, isChecked:Boolean){
        viewModelScope.launch {
            repository.updateItem(
                item = item.copy(isChecked = isChecked)
            )
        }
    }

    private fun fliterBy(shoppingListId:Int){
        if(shoppingListId != 10001){
            viewModelScope.launch {
                repository.getItemWithStoreAndListFilteredById(
                    shoppingListId
                ).collectLatest {
                    state = state.copy(items = it)
                }
            }
        }else{
            getItems()
        }
    }
}

data class HomeState(
    val items:List<ItemsWithStoreAndList> = emptyList(),
    val category: ULocale.Category = Category(),
    val itemChecked:Boolean = false
)