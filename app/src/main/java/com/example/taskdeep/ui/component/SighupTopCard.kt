package com.example.taskdeep.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R
import com.example.taskdeep.ui.theme.ColorTokens.Black
import com.example.taskdeep.ui.theme.TypoTokens.weight700size30

@Composable
internal fun SighupTopCard(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(117.dp))

        Text(
            text = stringResource(R.string.signup),
            style = weight700size30,
            color = Black,
            lineHeight = 30.sp,
            letterSpacing = (-0.3).sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SighupTopCardPreview() {
    SighupTopCard()
}