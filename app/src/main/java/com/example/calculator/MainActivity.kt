package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var tvDisplayResult: TextView? = null
    private var operand1: Double = -1.0
    private var operand2: Double = -1.0
    private var oneOperator: Boolean = false
    private var op1Str: String = ""
    private var op2Str: String = ""
    private var operator: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplayResult = findViewById(R.id.display_result)
    }

    fun onDigit(view: View) {
        if (operand2 == -1.0 || operand2 == -1.0)
            tvDisplayResult?.append((view as TextView).text)
    }

    fun onClear(view: View) {
        tvDisplayResult?.text = ""
        oneOperator = false
        operand1 = -1.0
        operand2 = -1.0
        operator = ""
    }

    fun onEqual(view: View) {
        if (oneOperator && operand1 != -1.0 && tvDisplayResult?.text.toString() != op1Str) {
            try {
                val range = op1Str.length + 1..<tvDisplayResult?.text?.length!!
                op2Str = tvDisplayResult?.text?.subSequence(range).toString()
                operand2 = op2Str.toDouble()
            } catch (e: Exception) {
                Log.d("TAG", "Invalid Entry in equal operator")
            }
        }
        if (oneOperator && operand1 != -1.0 && operand2 != -1.0 && operator != "") {
            tvDisplayResult?.text = when (operator) {
                "/" -> {
                    var strMessage: String
                    try {
                        strMessage = (operand1 / operand2).toString()
                    } catch (e: Exception) {
                        strMessage = "Infinity"
                        Log.d("TAG", "Some error occured on division.")
                    }
                    strMessage
                }

                "+" -> (operand1 + operand2).toInt().toString()
                "-" -> (operand1 - operand2).toInt().toString()
                "*" -> (operand1 * operand2).toInt().toString()
                else -> "Nothing"
            }
        }
    }

    fun onOperator(view: View) {
        if (!oneOperator && tvDisplayResult?.text?.isNotBlank() == true && operand1 == -1.0) {
            try {
                op1Str = tvDisplayResult?.text.toString()
                operand1 = op1Str.toDouble()
            } catch (e: Exception) {
                Log.d("TAG", "Can not converted from string to Double")
            }
            tvDisplayResult?.append((view as TextView).text)
            oneOperator = true
            operator = (view as TextView).text.toString()
        } else {
            Toast.makeText(this, "Operator can not be used here.", Toast.LENGTH_SHORT).show()
        }
    }
}