package com.naver.kot_first

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SavingUIStateDemoActivity : AppCompatActivity() {
    // (1)
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.savinguistate_demo)

        if(savedInstanceState != null) {
            num = savedInstanceState.getInt("num")
        }


        var numberText = findViewById<TextView>(R.id.number)
        var numberEdit = findViewById<EditText>(R.id.number_edit)
        var increaseButton = findViewById<Button>(R.id.increase)
        var setButton = findViewById<Button>(R.id.set_number)

        // (2)
        numberText.text = num.toString()

        // (3)
        increaseButton.setOnClickListener {
            num = numberText.text.toString().toInt() + 1
            numberText.text = num.toString()
        }
        setButton.setOnClickListener {
            num = numberEdit.text.toString().toInt()
            numberText.text = num.toString()
        }
        Log.d("mytag", "onCreate")
    }

    // onDestroy 재정의 및 로그 메시지 출력 코드 추가
    override fun onDestroy() {
        super.onDestroy()
        Log.d("mytag", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("mytag", "onSaveInstanceState")
        outState?.putInt("num", num)

    }

}
