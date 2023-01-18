package com.example.mindsharpener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var checkButton : Button
    lateinit var operand1: TextView
    lateinit var operand2: TextView
    lateinit var operator : TextView
    lateinit var userAnswer : EditText
    lateinit var easy : RadioButton
    lateinit var medium : RadioButton
    lateinit var hard : RadioButton
    lateinit var point : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkButton = findViewById(R.id.checkButton)
        operand1 = findViewById(R.id.operand1)
        operator = findViewById(R.id.operator)
        operand2 = findViewById(R.id.operand2)
        userAnswer = findViewById(R.id.yourAnswer)
        easy = findViewById(R.id.easy)
        medium = findViewById(R.id.medium)
        hard = findViewById(R.id.hard)
        point = findViewById(R.id.point)

        var randomOperator = "+-*/"
        var operatorGenerator = randomOperator.random()

        var randomOperand1 = Random.nextInt(0,9)
        var randomOperand2 = Random.nextInt(0,9)

        operand1.setText(randomOperand1.toString())
        operator.setText(operatorGenerator.toString())
        operand2.setText(randomOperand2.toString())
        point.setText("0")

        var answer = 0
        var currentPoint = 0
        checkButton.setOnClickListener(){
            answer = calculate(randomOperand1,operatorGenerator,randomOperand2)

            if(userAnswer.text.toString().toInt() == answer){
                currentPoint++
            }else{
                currentPoint--
            }
            point.setText(currentPoint.toString())

            operatorGenerator = randomOperator.random()

            if(easy.isChecked){
                randomOperand1 = Random.nextInt(0,9)
                randomOperand2 = Random.nextInt(1,9)
            }else if(medium.isChecked){
                randomOperand1 = Random.nextInt(0,99)
                randomOperand2 = Random.nextInt(1,99)
            }else if(hard.isChecked){
                randomOperand1 = Random.nextInt(0,999)
                randomOperand2 = Random.nextInt(1,999)
            }

            operand1.setText(randomOperand1.toString())
            operator.setText(operatorGenerator.toString())
            operand2.setText(randomOperand2.toString())

        }
    }

    fun calculate(operand1:Int,operator:Char,operand2:Int) : Int{
        var result = 999999999
        if(operator.equals('+')){
            result = operand1 + operand2
        }else if(operator.equals('-')){
            result = operand1 - operand2
        }else if(operator.equals('*')){
            result = operand1 * operand2
        }else if(operator.equals('/')){
            result = operand1 / operand2
        }else{

        }
        return result
    }
}