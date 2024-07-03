package com.sopt.now.compose.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.presentation.ui.signup.SignupActivity
import com.sopt.now.compose.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}

@Preview
@Composable
fun Login() {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val authService by lazy { ServicePool.authService }

    var shouldShowPassword by remember {
        mutableStateOf(false)
    }
    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_baseline_visibility_white_24
        } else {
            R.drawable.ic_baseline_visibility_off_white_24
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.tv_login_title),
            fontSize = 30.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
        )
        Text(
            text = stringResource(id = R.string.tv_login_id),
            fontSize = 20.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp)
        )
        TextField(
            value = id,
            onValueChange = {
                id = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            label = { Text(stringResource(id = R.string.et_login_id_hint)) }
        )
        Text(
            text = stringResource(id = R.string.tv_login_pw),
            fontSize = 20.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        TextField(
            value = pw,
            onValueChange = {
                pw = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (shouldShowPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            label = { Text(stringResource(id = R.string.et_login_pw_hint)) },
            trailingIcon = {
                IconButton(onClick = {
                    shouldShowPassword = !shouldShowPassword
                }) {
                    Icon(
                        painter = painterResource(
                            id = passwordResource(shouldShowPassword)
                        ),
                        contentDescription = null
                    )
                }
            }
        )
        Button(
            onClick = {
//                val requestLoginDto = RequestLoginDto(id, pw)
//                authService.login(requestLoginDto).enqueue(object : Callback<ResponseSignupDto> {
//                    override fun onResponse(
//                        call: Call<ResponseSignupDto>,
//                        response: Response<ResponseSignupDto>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data: ResponseSignupDto? = response.body()
//                            val userId = response.headers()["location"]
//
//                            val intent = Intent(context, MainActivity::class.java)
//                            intent.putExtra("userId", userId)
//
//                            Toast.makeText(context, "$userId 님 로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
//                            context.startActivity(intent)
//                        } else {
//                            liveData.value = SignupState(true, "아이디와 비밀번호가 일치하지 않습니다")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
//                        liveData.value = SignupState(true, "서버 통신 에러")
//                    }
//                })
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF209672)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_login))
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
            Text(text = stringResource(id = R.string.btn_signup), color = Color.Black)
        }
    }
}