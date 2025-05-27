package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val myCheckBox = findViewById<CheckBox>(R.id.checkBox)
        val myTextView = findViewById<TextView>(R.id.textview)
        myCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                myTextView.text = "Zaznaczone"
            } else {
                myTextView.text ="Odznaczone"
            }

        }
        lateinit var timerTextView: TextView
        lateinit var mainLayout: LinearLayout
        timerTextView = findViewById(R.id.timerCounter)
        mainLayout = findViewById(R.id.main)

        val countDownTimer: CountDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerTextView.text = "Pozostało $secondsLeft sekund"
                mainLayout.setBackgroundColor(
                    Color.rgb(
                        Random.nextInt(255),
                        Random.nextInt(255) ,
                        Random.nextInt(255)
                    ))

            }

            override fun onFinish() {
                timerTextView.text = "Koniec czasu"
            }
        }

        countDownTimer.start()


        val spinner: Spinner = findViewById(R.id.spinnerColors)
        val colors = arrayOf("Ulubiony kolor?", "Bialy", "Czarny", "Zielony", "Niebieski")

        val adapter = ArrayAdapter( this,
            android.R.layout.simple_list_item_1,
            colors
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedColor = colors[position]
                Toast.makeText(this@MainActivity, "Wybrałeś: $selectedColor", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val radioGroup = findViewById<RadioGroup>(R.id.platki)
        val button = findViewById<Button>(R.id.button_submit)
        button.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val odpowiedz = selectedRadioButton.text.toString()

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("odpowiedz", odpowiedz)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Proszę zaznaczyć odpowiedź!", Toast.LENGTH_SHORT).show()
            }
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_second)

            val odpowiedz = intent.getStringExtra("odpowiedz")

            val wynikTextView = findViewById<TextView>(R.id.wynik)
            wynikTextView.text = "Wybrałeś: $odpowiedz"



        }}}



