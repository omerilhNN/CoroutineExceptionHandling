package com.omrilhn.coroutineexceptionhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.omrilhn.coroutineexceptionhandling.ui.theme.CoroutineExceptionHandlingTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler =  CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception" + throwable.localizedMessage)
        }

        lifecycleScope.launch(handler) {
            throw Exception("error") // WHEN YOU HANDLE COROUTINEEXPETIONS WITH THE HANDLER ABOVE HERE,
            //THEN APP DOESNT HAVE TO FALL APART
        }
        lifecycleScope.launch(handler)
        {
            supervisorScope {
                launch{
                    throw  Exception("Error") //IF YOU DONT USE SUPERVISOR SCOPE THEN CODES DOWN BELOW WONT BE INITIALIZED
                }
                launch {
                    delay(3000)
                    println("this is executed")
                }
            }

        }
    }

}


