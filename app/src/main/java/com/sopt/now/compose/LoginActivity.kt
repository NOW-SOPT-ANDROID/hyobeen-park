package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login(
                        signupId = intent.getStringExtra("id"),
                        signupPw = intent.getStringExtra("pw"),
                        nickname = intent.getStringExtra("nickname"),
                        mbti = intent.getStringExtra("mbti")
                    )
                }
            }
        }
    }
}

@Composable
fun Login(signupId : String?, signupPw : String?, nickname : String?, mbti : String?) {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "Welcome To SOPT",
            fontSize = 30.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "ID",
            fontSize = 20.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = id,
            onValueChange = {
                id = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {Text("아이디를 입력해주세요")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = pw,
            onValueChange = {
                pw = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {Text("비밀번호를 입력해주세요")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if(id == signupId && pw == signupPw) {
                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("pw", pw)
                    intent.putExtra("nickname", nickname)
                    intent.putExtra("mbti", mbti)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF209672)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "로그인")
        }
        Button(
            onClick = {
                val intent = Intent(context, SignupActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.align(Alignment.End)
            ) {
            Text(text = "회원가입하기", color = Color.Black)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LoginPreview() {
    Login(null, null, null, null)
}