package com.example.quizapp

import androidx.annotation.StringRes

data class Question(@StringRes val textRes: Int, val answer: Boolean)