package com.example.administrator.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs


class MainActivity : AppCompatActivity() {

    var total: Double = 0.0
    var currNum: Double = 0.0
    var decimalPressed: Boolean = false
    var currOp : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateView()

        numBtn0.setOnClickListener({ handleNumClick(0.0) })
        numBtn1.setOnClickListener({ handleNumClick(1.0) })
        numBtn2.setOnClickListener({ handleNumClick(2.0) })
        numBtn3.setOnClickListener({ handleNumClick(3.0) })
        numBtn4.setOnClickListener({ handleNumClick(4.0) })
        numBtn5.setOnClickListener({ handleNumClick(5.0) })
        numBtn6.setOnClickListener({ handleNumClick(6.0) })
        numBtn7.setOnClickListener({ handleNumClick(7.0) })
        numBtn8.setOnClickListener({ handleNumClick(8.0) })
        numBtn9.setOnClickListener({ handleNumClick(9.0) })
        numBtnDec.setOnClickListener { handleDecimalClick() }
        numBtnPlus.setOnClickListener { handleOperatorClick("plus") }
        numBtnMinus.setOnClickListener { handleOperatorClick("minus") }
        numBtnMult.setOnClickListener { handleOperatorClick("mult") }
        numBtnDiv.setOnClickListener { handleOperatorClick("div") }
        numBtnSign.setOnClickListener { handleOperatorClick("sign") }
        equalsBtn.setOnClickListener { calcTotal() }
    }


    fun changeNumVal(num: Double) {
        val num = num.toInt()
        val currNumIsNegative = currNum < 0
        if (currNum.toInt() == 0) {
            currNum = if (decimalPressed || hasDecimalVals(currNum)) constructNum(currNum, num) else num.toDouble()
        } else {
            if (currOp != "") {
                if (total.toInt() == 0) {
                    total = currNum
                    currNum = if (decimalPressed) ("0.$num").toDouble() else num.toDouble()
                } else {
                    currNum = constructNum(currNum, num)
                }
            } else {
                currNum = constructNum(currNum, num)
            }
        }
        decimalPressed = false
    }

    fun handleNumClick(num: Double) {
        changeNumVal(num)
        updateView()
    }

    fun splitDecimal(num: Double): Pair<Int, Int> {
        val sides = num.toString().split(".")
        return Pair(sides[0].toInt(), sides[1].toInt())
    }

    fun constructNum(numOne: Double, numTwo: Int): Double {
        val (whole: Int, decimal: Int) = splitDecimal(numOne)
        var newWhole : String = "$whole"
        var newDecimal: String = "$decimal"
        if (decimalPressed || hasDecimalVals(numOne)) {
            newDecimal = if (decimal > 0) "$decimal$numTwo" else "$numTwo"
        } else {
            newWhole = "$whole$numTwo"
        }
        return ("$newWhole.$newDecimal").toDouble()
    }

    fun hasDecimalVals(num: Double): Boolean {
        val num = abs(num)
        return num - num.toInt() > 0
    }

    fun handleDecimalClick() {
        decimalPressed = true
    }

    fun handleOperatorClick(op: String) {
        if (op == "sign") {
            currNum = -currNum
            updateView()
        } else {
            if (currOp != "") {
                calcTotal()
            }
            currOp = op
        }
    }

    fun execOperation(operand1: Double?, operand2: Double?, operator: String) : Double {
        if (operand1 != null && operand2 != null) {
            return when (operator) {
                "plus" -> operand1 + operand2
                "minus" -> operand1 - operand2
                "mult" -> operand1 * operand2
                "div" -> operand1 / operand2
                else -> currNum
            }
        } else {
            return 0.0
        }
    }

    fun clear(v: View) {
        total = 0.0
        currNum = 0.0
        decimalPressed = false
        currOp = "";
        updateView()
    }

    fun updateView() {
        val viewNum = if (hasDecimalVals(currNum)) currNum else currNum.toInt()
        resultView.setText("$viewNum")
    }

    fun calcTotal() {
        currNum = execOperation(total, currNum, currOp)
        updateView()
        currOp = ""
        total = 0.0
    }

}
