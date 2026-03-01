package com.example.taskdeep.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R
import com.example.taskdeep.ui.theme.ColorTokens.Black
import com.example.taskdeep.ui.theme.ColorTokens.Blue_2735AE
import com.example.taskdeep.ui.theme.ColorTokens.Grey_999999
import com.example.taskdeep.ui.theme.TypoTokens.weight400size15
import com.example.taskdeep.ui.theme.TypoTokens.weight700size30

@Composable
internal fun TopCardWithBack(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    progress: Float = 1f,
    onBackClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(24.dp))

        Icon(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable { onBackClick() },
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "뒤로가기",
            tint = Black,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(5.dp)
                .background(color = Blue_2735AE)
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = title,
            style = weight700size30,
            color = Black,
            lineHeight = 30.sp,
            letterSpacing = (-0.3).sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = description,
            style = weight400size15,
            color = Grey_999999,
            lineHeight = 24.sp,
            letterSpacing = (-0.15).sp
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TopCardWithBackPreview() {
    TopCardWithBack(
        title = "회원가입",
        description = "회원가입을 진행합니다."
    )
}