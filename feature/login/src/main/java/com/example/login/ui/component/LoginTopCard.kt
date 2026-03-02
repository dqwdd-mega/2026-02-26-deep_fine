package com.example.login.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R
import com.example.login.ui.theme.ColorTokens.Black
import com.example.login.ui.theme.ColorTokens.Grey_999999
import com.example.login.ui.theme.TypoTokens.weight400size15
import com.example.login.ui.theme.TypoTokens.weight700size30

@Composable
internal fun LoginTopCard(
    modifier: Modifier = Modifier,
    isSignupMode: Boolean = false,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        if (showBackButton) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Icon(
                modifier = Modifier
                    .clickable { onBackClick() },
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "뒤로가기",
                tint = Black,
            )
            
            Spacer(modifier = Modifier.height(68.dp))
        } else {
            Spacer(modifier = Modifier.height(116.dp))
        }

        Text(
            text = stringResource(
                if (isSignupMode) R.string.signup else R.string.login_signup
            ),
            style = weight700size30,
            color = Black,
            lineHeight = 30.sp,
            letterSpacing = (-0.3).sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (!isSignupMode) {
            Text(
                text = stringResource(R.string.login_description),
                style = weight400size15,
                color = Grey_999999,
                lineHeight = 24.sp,
                letterSpacing = (-0.15).sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun LoginTopCardPreview() {
    LoginTopCard()
}
