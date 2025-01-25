package com.egorpoprotskiy.unit3pathway3productivity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Productivity(
    @DrawableRes
    val iconId: Int,
    @StringRes
    val dayHead: Int,
    @StringRes
    val day: Int
)