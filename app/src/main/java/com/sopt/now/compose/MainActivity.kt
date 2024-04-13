package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(
                        intent.getStringExtra("id")!!,
                        intent.getStringExtra("pw")!!,
                        intent.getStringExtra("nickname")!!,
                        intent.getStringExtra("mbti")!!
                    )
                }
            }
        }
    }
}

@Composable
fun Main(id : String = "", pw : String = "", nickname : String = "", mbti : String = "") {
    Column (
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Android",
            Modifier
                .width(200.dp)
                .height(200.dp)
                .background(color = Color(0xFF209672))
        )
        Text(
            text = nickname,
            fontSize = 30.sp,
            fontWeight = Bold,
            modifier = Modifier.padding(top = 30.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "ID : ",
                fontSize = 30.sp,
                fontWeight = Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = id,
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "MBTI : ",
                fontSize = 30.sp,
                fontWeight = Bold,
                textAlign = TextAlign.Start
            )
            Text(
                text = mbti,
                fontSize = 30.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NOWSOPTAndroidTheme {
        Main()
    }
}