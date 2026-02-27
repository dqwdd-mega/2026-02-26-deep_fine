package com.example.taskdeep.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R
import com.example.taskdeep.ui.theme.ColorTokens.Grey_C2C3CA
import com.example.taskdeep.ui.theme.ColorTokens.White
import com.example.taskdeep.ui.theme.Radius.Round12
import com.example.taskdeep.ui.theme.TypoTokens.weight400size18

@Composable
internal fun LoginButtonComponent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
                text = stringResource(R.string.login_signup),
                style = weight400size18,
                color = White,
                lineHeight = 18.sp,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonComponentPreview() {
    LoginButtonComponent()
}