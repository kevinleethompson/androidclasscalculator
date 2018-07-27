package com.example.administrator.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currNum: Double = 0.0
    var operatorPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeNumVal(num: Double) {
        if (currNum == 0.0) {
            currNum = num
        } else if (currNum > 0.0) {
            if (operatorPressed) {
                currNum = num
            } else {
                currNum = (currNum.toString() + num).toDouble()
            }
        }
    }

    fun handleOperatorClick() {
        numBtnDec
    }

    fun calc() {

    }
}
