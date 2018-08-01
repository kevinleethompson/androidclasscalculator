package com.example.administrator.calculator

import android.view.View
import android.widget.Button
import org.jetbrains.anko.toast

class Presenter {

    fun onBtnClick(v: View, id: String) {
        v.context.toast(id)
    }

}