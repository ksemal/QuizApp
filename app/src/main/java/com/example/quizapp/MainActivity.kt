package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    lateinit var prevBtn: ImageButton
    lateinit var nextBtn: ImageButton
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    lateinit var question: TextView
    private var score = 0

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentQuestionIndex)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(TAG, 0) ?: 0
        quizViewModel.currentQuestionIndex = currentIndex

        prevBtn = findViewById(R.id.btnLeft)
        nextBtn = findViewById(R.id.btnRight)
        trueBtn = findViewById(R.id.btn_true)
        falseBtn = findViewById(R.id.btn_false)
        question = findViewById(R.id.textView)

        getQuestion()

        nextBtn.setOnClickListener {
            updateQuestion(1)
        }

        prevBtn.setOnClickListener {
            updateQuestion(-1)
        }

        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }


    }

    private fun updateQuestion(questionCount: Int) {
        if (quizViewModel.questionList.size == 0) {
            question.text = resources.getString(R.string.score, score)
        } else {
            quizViewModel.calculateIndex(questionCount)
            getQuestion()
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (quizViewModel.currentQuestionAnswer == userAnswer) {
            Toast.makeText(this, R.string.answer_right, Toast.LENGTH_SHORT).show()
            score++
        } else {
            Toast.makeText(this, R.string.answer_wrong, Toast.LENGTH_SHORT).show()
        }
        falseBtn.isEnabled = false
        trueBtn.isEnabled = false
        quizViewModel.questionList.removeAt(quizViewModel.currentQuestionIndex)
    }

    private fun getQuestion() {
        question.text = resources.getString(quizViewModel.currentQuestionText)
        trueBtn.isEnabled = true
        falseBtn.isEnabled = true
    }
}
