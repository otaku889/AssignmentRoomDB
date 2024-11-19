package com.example.practicecompose.view.activities

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import coil.size.Size
import com.example.practicecompose.R
import com.example.practicecompose.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private  val viewModel: HomeViewModel by viewModels()

    private var name : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
        viewModel.fetchData()
            viewModel.Emails.observe(this@MainActivity){
                name = it
            }
            setContent {
                MaterialTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
           delay(3000)
            setContent {
                MaterialTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column { Loading()
                            Row{
                                Greeting(
                                    name = name,
                                    modifier = Modifier.padding(innerPadding)
                                        .fillMaxWidth()

                                )
                            }
            }
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("YUKI","onStart ${viewModel.Emails.value}")
    }
    override fun onResume() {
        super.onResume()
        Log.i("YUKI","onResume ${viewModel.Emails.value}")
    }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

Box (
    contentAlignment = Alignment.Center
){
    Text(
        text = "Hello $name!",
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(5.dp)

    )
}

}

@Composable
fun Loading(
            modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.password_asset).apply(block = {
                size(Size.ORIGINAL)
                repeatCount(0)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Column { Loading()
            Row{ Greeting(
                name = "name") }
        }
    }
}
