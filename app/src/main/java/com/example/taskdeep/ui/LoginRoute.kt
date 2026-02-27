package com.example.taskdeep.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskdeep.ui.theme.ColorTokens.Black
import com.example.taskdeep.ui.theme.ColorTokens.Grey_999999
import com.example.taskdeep.ui.theme.ColorTokens.Grey_C2C3CA
import com.example.taskdeep.ui.theme.ColorTokens.White
import com.example.taskdeep.ui.theme.Radius.Round12
import com.example.taskdeep.ui.theme.TypoTokens.weight400size13_notoSansKr
import com.example.taskdeep.ui.theme.TypoTokens.weight400size15
import com.example.taskdeep.ui.theme.TypoTokens.weight400size18
import com.example.taskdeep.ui.theme.TypoTokens.weight700size30
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is LoginContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginScreen()
}

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column {

            Spacer(modifier = Modifier.height(116.dp))

            Text(
                text = "로그인/회원가입",
                style = weight700size30,
                color = Black,
                lineHeight = 30.sp,
                letterSpacing = (-0.3).sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "로그인 또는 회원가입을 위해 이메일을 입력해주세요.",
                style = weight400size15,
                color = Grey_999999,
                lineHeight = 24.sp,
                letterSpacing = (-0.15).sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "이메일",
                style = weight400size13_notoSansKr,
                color = Black,
                lineHeight = 13.sp,
            )

            Text(
                text = "이메일 주소를 입력하세요",
                style = weight400size18,
                color = Grey_999999,
                lineHeight = 24.sp,
                letterSpacing = (-0.15).sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(color = Grey_C2C3CA, shape = Round12)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "로그인/회원가입",
                    style = weight400size18,
                    color = White,
                    lineHeight = 18.sp,
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}