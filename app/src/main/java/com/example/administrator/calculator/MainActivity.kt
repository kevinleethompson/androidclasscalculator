package com.example.administrator.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var total: Double = 0.0
    var currNum: Double = 0.0
    var decimal: Boolean = false
    var currOp : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView.setText("$currNum")

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
        if (currNum.toInt() == 0) {
            toast("TRUE!")
            currNum = if (decimal) ("0.$num").toDouble() else num.toDouble()
        } else if (currNum.toInt() > 0) {
            if (currOp != "") {
                if (total.toInt() == 0) {
                    total = currNum
                    currNum = num.toDouble()
                } else {
                    val sides = currNum.toString().split(".")
                    currNum = if (sides[1].toInt() > 0) ("${sides[0]}.${sides[1]}$num").toDouble() else ("${sides[0]}$num.0").toDouble()
                }
            } else {
                total = currNum
                val sides = currNum.toString().split(".")
                currNum = if (sides[1].toInt() > 0) ("${sides[0]}.${sides[1]}$num").toDouble() else ("${sides[0]}$num.0").toDouble()
            }
        }
        decimal = false
    }

    fun handleNumClick(num: Double) {
        changeNumVal(num)
        updateView()
    }

    fun handleDecimalClick() {
        decimal = true
    }

    fun handleOperatorClick(op: String) {
        if (currOp != "") { calcTotal() }
        currOp = op
    }

    fun execOperation(operand1: Double?, operand2: Double?, operator: String) : Double {
        if (operator == "sign") {
            return -currNum
        } else if (operand1 != null && operand2 != null) {
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

    fun updateView() {
        resultView.setText("$currNum")
    }

    fun calcTotal() {
        currNum = execOperation(total, currNum, currOp)
        updateView()
        currOp = ""
        total = 0.0
    }

}
