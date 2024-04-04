package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Signup()
                }
            }
        }
    }
}

@Composable
fun Signup() {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "SIGN UP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "ID",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
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
            label = { Text("아이디를 입력해주세요") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "비밀번호",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
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
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("비밀번호를 입력해주세요") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "닉네임",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = nickname,
            onValueChange = {
                nickname = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("닉네임을 입력해주세요") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "MBTI",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = mbti,
            onValueChange = {
                mbti = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text( "MBTI를 입력해주세요") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                when {
                    id.isBlank() || id.length < 6 || id.length > 10 ->
                        Toast.makeText(context, "아이디는 6글자 이상, 10글자 이하로 입력해주세요", Toast.LENGTH_SHORT).show()
                    pw.isBlank() || pw.length < 8 || pw.length > 12 ->
                        Toast.makeText(context, "비밀번호는 8글자 이상, 12글자 이하로 입력해주세요", Toast.LENGTH_SHORT).show()
                    nickname.isBlank() ->
                        Toast.makeText(context, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
                    mbti.isBlank() ->
                        Toast.makeText(context, "mbti를 입력해주세요", Toast.LENGTH_SHORT).show()
                    else -> {
                        Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("pw", pw)
                        intent.putExtra("nickname", nickname)
                        intent.putExtra("mbti", mbti)
                        context.startActivity(intent)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF209672)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "회원가입하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Signup()
}