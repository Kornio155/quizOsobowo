package com.example.quiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val wynikTextView = findViewById<TextView>(R.id.wynik)

        val odpowiedz = intent.getStringExtra("odpowiedz") ?: ""

        val wynik = when (odpowiedz) {
            "Najpierw mleko" -> "Strateg! Zanim wrzucisz płatki, analizujesz sytuację. Prawdopodobnie masz Excela na śniadanie."
            "Najpierw platki" -> "Improwizator! Najpierw akcja, potem refleksja. Gdyby płatki były przygodą — Ty byłbyś ich kapitanem."
            "Nie jem platkow" -> "Zen Master. Podczas gdy inni walczą o proporcje mleka do płatków, Ty już dawno osiągnąłeś wewnętrzny spokój i zjadłeś jajecznicę."
            else -> "Nie wybrano odpowiedzi."
        }


        wynikTextView.text = wynik
    }
}