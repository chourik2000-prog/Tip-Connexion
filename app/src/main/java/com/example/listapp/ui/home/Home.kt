package com.example.listapp.ui.home

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    onNavigate:(Int) -> Unit
){
    val homeViewModel = viewModel(modelClass = HomeViewModel::Class.java)
    val homeState = homeViewModel.state
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                
            }
        }
    )
}