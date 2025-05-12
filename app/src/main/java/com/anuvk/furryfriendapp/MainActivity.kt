package com.anuvk.furryfriendapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.anuvk.furryfriendapp.presentation.AppNavigation
import com.anuvk.furryfriendapp.presentation.viewmodel.DogBreedViewModel
import com.anuvk.furryfriendapp.ui.theme.FurryFriendAppTheme
import com.anuvk.furryfriendapp.ui.theme.Purple40
import com.anuvk.furryfriendapp.ui.theme.Purple80
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dogBreedViewModel by viewModels<DogBreedViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dogBreedViewModel.loadAllDogBreeds()
                dogBreedViewModel.state.collectLatest {
                    Log.d(">>>", "State: $it")
                }
            }
        }
        enableEdgeToEdge()
        setContent {
            FurryFriendAppTheme {
            val navController = rememberNavController()

            Scaffold(
            topBar = { TopAppBar(title = { Text("My App") }) },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    AppNavigation(
                        navController = navController)
                }
            })
         }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FurryFriendAppTheme {
        Greeting("Android")
    }
}