package com.sopt.now.compose.presentation.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.data.datasourceImpl.AuthRemoteDatasourceImpl
import com.sopt.now.compose.data.model.request.RequestSignupDto
import com.sopt.now.compose.data.repository.AuthRepositoryImpl
import com.sopt.now.compose.domain.model.User
import com.sopt.now.compose.presentation.ui.login.LoginActivity
import com.sopt.now.compose.theme.NOWSOPTAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

class SignupActivity : ComponentActivity() {
    val signupViewModel = SignupViewModel(AuthRepositoryImpl(AuthRemoteDatasourceImpl()))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Signup(signupViewModel)
                }
            }
        }
    }
}


@Composable
fun Signup(signupViewModel: SignupViewModel) {
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

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
            text = stringResource(id = R.string.tv_signup_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp)
        )
        Text(
            text = stringResource(id = R.string.tv_login_id),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
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
            fontWeight = FontWeight.Bold,
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
        Text(
            text = stringResource(id = R.string.tv_signup_nickname),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        TextField(
            value = nickname,
            onValueChange = {
                nickname = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            label = { Text(stringResource(id = R.string.et_signup_nickname_hint)) }
        )
        Text(
            text = stringResource(id = R.string.tv_signup_phone),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        TextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            label = { Text(stringResource(id = R.string.et_signup_phone_hint)) }
        )
        Button(
            onClick = {
                val validationMsg = signupViewModel.checkSignupValidation(id, pw, nickname, phone)
                if (validationMsg == R.string.signup_success) {
                    val user = User(R.drawable.ic_baseline_visibility_white_24, id, nickname, phone)
                    signupViewModel.postSignup(user, pw)
                    context.startActivity(Intent(context, LoginActivity::class.java))
                } else {
                    Toast.makeText(context, validationMsg, Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF209672)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_signup))
        }
    }
}