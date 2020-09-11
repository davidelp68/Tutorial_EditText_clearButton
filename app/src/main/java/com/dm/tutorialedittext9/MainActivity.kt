package com.dm.tutorialedittext9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable                        //Importazione della Classe: Editable
import android.text.TextWatcher                     //Importazione della Classe: TextWatcher
import android.view.MotionEvent                     //Importazione della Classe: MotionEvent
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Imposta l'EditText con il pulsante cancella contenuto chiamando la funzione
        //setupClearButtonWithAction()
        editText.setupClearButtonWithAction()
    }

    //funzione che permette di inserire il pulsante cancella nell'EditText
    private fun EditText.setupClearButtonWithAction() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_clear else 0
                //ic_clear è il nome dell'icona creata all'interno della cartella drawable
                setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        //gestisce il clic sul pulsante cancella
        setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    //cancella il contenuto dell'EditText
                    this.setText("")
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }
}
