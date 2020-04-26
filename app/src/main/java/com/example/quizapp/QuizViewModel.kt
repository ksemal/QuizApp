package com.example.quizapp

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentQuestionIndex = 0

    val questionList = mutableListOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, true),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false)
    )

    val currentQuestionAnswer
    get() = questionList[currentQuestionIndex].answer

    val currentQuestionText
    get() = questionList[currentQuestionIndex].textRes

    fun calculateIndex(questionCount: Int) {
        currentQuestionIndex = (currentQuestionIndex + questionCount) % questionList.size
    }
}