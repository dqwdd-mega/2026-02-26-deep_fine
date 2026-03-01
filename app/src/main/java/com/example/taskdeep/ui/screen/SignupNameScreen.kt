package com.example.taskdeep.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskdeep.R
import com.example.taskdeep.ui.component.LoginButtonComponent
import com.example.taskdeep.ui.component.NameInputComponent
import com.example.taskdeep.ui.component.TopCardWithBack

@Composable
fun SignupNameScreen(
    inputName: String = "",
    isNameFocused: Boolean = false,
    showNameState: Boolean = false,
    onChangeName: (String) -> Unit = {},
    onChangeNameFocus: (Boolean) -> Unit = {},
    onClickLoginButton: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column {
            TopCardWithBack(
                title = stringResource(R.string.login_signup),
                description = stringResource(R.string.login_description),
                progress = 1f / 3f,
                onBackClick = onClickBackButton
            )
            
            NameInputComponent(
                modifier = Modifier.padding(horizontal = 24.dp),
                value = inputName,
                onValueChange = onChangeName,
                isFocused = isNameFocused,
                onFocusChange = onChangeNameFocus,
                showNameState = showNameState
            )
            
            LoginButtonComponent(
                modifier = Modifier.padding(horizontal = 24.dp),
                buttonText = stringResource(R.string.next),
                isEnabled = inputName.isNotEmpty(),
                onClick = onClickLoginButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupNameScreenPreview() {
    SignupNameScreen()
}