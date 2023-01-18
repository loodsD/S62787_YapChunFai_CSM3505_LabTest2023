package com.example.mindsharpener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //initialization the object in this class
    lateinit var checkButton : Button
    lateinit var operand1: TextView
    lateinit var operand2: TextView
    lateinit var operator : TextView
    lateinit var userAnswer : EditText
    lateinit var radioGroup : RadioGroup
    lateinit var easy : RadioButton
    lateinit var medium : RadioButton
    lateinit var hard : RadioButton
    lateinit var point : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Link the object in this class with the XML from activity_main
        checkButton = findViewById(R.id.checkButton)
        operand1 = findViewById(R.id.operand1)
        operator = findViewById(R.id.operator)
        operand2 = findViewById(R.id.operand2)
        userAnswer = findViewById(R.id.yourAnswer)
        radioGroup = findViewById(R.id.radiogroup)
        easy = findViewById(R.id.easy)
        medium = findViewById(R.id.medium)
        hard = findViewById(R.id.hard)
        point = findViewById(R.id.point)

        //HERE IS ACTION BY DEFAULT, when USER FIRST TIME ENTER, THE difficulty is set AS EASY
        //declare a variable for random Operator
        var randomOperator = "+-*/"
        //Generate either of it
        var operatorGenerator = randomOperator.random()

        //Generate random operands
        var randomOperand1 = Random.nextInt(0,9)
        var randomOperand2 = Random.nextInt(0,9)

        //set the textView in the activity_main with the random generator
        operand1.setText(randomOperand1.toString())
        operator.setText(operatorGenerator.toString())
        operand2.setText(randomOperand2.toString())
        point.setText("0")

        var answer = 0
        var currentPoint = 0

        //Add small message to the radioGroup, when users try to change the difficulty
        radioGroup.setOnCheckedChangeListener{group, checkedId ->
            if(easy.id == group.checkedRadioButtonId){
                Toast.makeText(this,"From now on the difficulty is EASY",Toast.LENGTH_SHORT).show()
            }else if(medium.id == group.checkedRadioButtonId){
                Toast.makeText(this,"From now on the diffculty is MEDIUM",Toast.LENGTH_SHORT).show()
            }else if(hard.id == group.checkedRadioButtonId){
                Toast.makeText(this,"From now on the difficulty is HARD",Toast.LENGTH_SHORT).show()
            }
        }

        //Add listener to the check button to check the answer
        checkButton.setOnClickListener(){

            //calculate the answer based on the generator
            answer = calculate(randomOperand1,operatorGenerator,randomOperand2)

            //if the answer matches, add 1 point, else deduct 1 point
            if(userAnswer.text.toString().toInt() == answer){
                currentPoint++
            }else{
                currentPoint--
            }
            //Set TextView for the point
            point.setText(currentPoint.toString())

            //Generator operator for the next math problem
            operatorGenerator = randomOperator.random()

            //This is to check the radio button, if easy is checked, then generate the operands with 1 digit,
            //if medium is checked, then generate the operands with 2 digits,
            //if hard is checked, then generate the operands wtih 3 digits
            if(easy.isChecked){
                randomOperand1 = Random.nextInt(0,9)
                randomOperand2 = Random.nextInt(1,9)
            }else if(medium.isChecked){
                randomOperand1 = Random.nextInt(10,99)
                randomOperand2 = Random.nextInt(10,99)

            }else if(hard.isChecked){
                randomOperand1 = Random.nextInt(100,999)
                randomOperand2 = Random.nextInt(100,999)
            }

            //Set the TextView with the new generators of operands and operator
            operand1.setText(randomOperand1.toString())
            operator.setText(operatorGenerator.toString())
            operand2.setText(randomOperand2.toString())

            //Add small message to the radioGroup again, when users try to change the difficulty
            radioGroup.setOnCheckedChangeListener{group, checkedId ->
                if(easy.id == group.checkedRadioButtonId){
                    Toast.makeText(this,"From now on the difficulty is EASY",Toast.LENGTH_SHORT).show()
                }else if(medium.id == group.checkedRadioButtonId){
                    Toast.makeText(this,"From now on the diffculty is MEDIUM",Toast.LENGTH_SHORT).show()
                }else if(hard.id == group.checkedRadioButtonId){
                    Toast.makeText(this,"From now on the difficulty is HARD",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //This is the function for calculate the operations.
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
        }
        return result
    }
}