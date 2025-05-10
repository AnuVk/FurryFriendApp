package com.anuvk.furryfriendapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.anuvk.furryfriendapp.presentation.viewmodel.DogBreedViewModel
import com.anuvk.furryfriendapp.ui.theme.DogBreedsListScreen
import com.anuvk.furryfriendapp.ui.theme.DogBreedsScreen
import com.anuvk.furryfriendapp.ui.theme.FurryFriendAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dogBreedViewModel by viewModels<DogBreedViewModel>()

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
                DogBreedsScreen(dogBreedViewModel)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
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