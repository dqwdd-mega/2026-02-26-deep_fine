package com.example.taskdeep.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskdeep.R

object FontSizeTokens {
    val textSize30 = 30.sp
    val textSize18 = 18.sp
    val textSize15 = 15.sp
    val textSize13 = 13.sp
}

object TypoTokens {
    private val notoSansKrTextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.noto_sans_kr_900w, FontWeight.Black),
            Font(R.font.noto_sans_kr_800w, FontWeight.ExtraBold),
            Font(R.font.noto_sans_kr_700w, FontWeight.Bold),
            Font(R.font.noto_sans_kr_600w, FontWeight.SemiBold),
            Font(R.font.noto_sans_kr_500w, FontWeight.Medium),
            Font(R.font.noto_sans_kr_400w, FontWeight.Normal),
            Font(R.font.noto_sans_kr_300w, FontWeight.Light),
            Font(R.font.noto_sans_kr_200w, FontWeight.ExtraLight),
            Font(R.font.noto_sans_kr_100w, FontWeight.Thin),
        ),
    )

    val weight700size30 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeTokens.textSize30,
    )

    val weight400size13_notoSansKr = notoSansKrTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.textSize13,
    )

    val weight400size15 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.textSize15,
    )

    val weight400size18 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeTokens.textSize18,
    )
}