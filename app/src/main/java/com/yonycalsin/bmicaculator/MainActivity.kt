package com.yonycalsin.bmicaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

enum class GenderCard {
    MALE,
    FEMALE
}

class MainActivity : AppCompatActivity() {
    private lateinit var cardMale: CardView

    private lateinit var cardFemale: CardView

    private lateinit var textHeight: TextView

    private lateinit var rangeSliderHeight: RangeSlider

    private lateinit var textViewWeight: TextView

    private lateinit var btnDecrementWeight: FloatingActionButton

    private lateinit var btnIncrementWeight: FloatingActionButton

    private lateinit var textViewAge: TextView

    private lateinit var btnDecrementAge: FloatingActionButton

    private lateinit var btnIncrementAge: FloatingActionButton

    private lateinit var btnCalculate: Button

    private var selectedGenderCard: Enum<GenderCard> = GenderCard.MALE

    private var currentHeight: Int = 120

    private var currentWeight: Int = 70

    private var currentAge: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initComponents()

        initListeners()

        initUI()
    }

    private fun initComponents() {
        cardMale = findViewById(R.id.cardMale)

        cardFemale = findViewById(R.id.cardFemale)

        textHeight = findViewById(R.id.textHeight)

        rangeSliderHeight = findViewById(R.id.rangeSliderHeight)

        textViewWeight = findViewById(R.id.textViewWeight)

        btnDecrementWeight = findViewById(R.id.btnDecrementWeight)

        btnIncrementWeight = findViewById(R.id.btnIncrementWeight)

        textViewAge = findViewById(R.id.textViewAge)

        btnDecrementAge = findViewById(R.id.btnDecrementAge)

        btnIncrementAge = findViewById(R.id.btnIncrementAge)

        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        cardMale.setOnClickListener {
            selectedGenderCard = GenderCard.MALE

            setCardBackgroundColor()
        }

        cardFemale.setOnClickListener {
            selectedGenderCard = GenderCard.FEMALE

            setCardBackgroundColor()
        }

        rangeSliderHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()

            setTextHeight()
        }

        btnDecrementWeight.setOnClickListener {
            currentWeight -= 1

            setTextWeight()
        }

        btnIncrementWeight.setOnClickListener {
            currentWeight += 1

            setTextWeight()
        }

        btnDecrementAge.setOnClickListener {
            currentAge -= 1

            setTextAge()
        }

        btnIncrementAge.setOnClickListener {
            currentAge += 1

            setTextAge()
        }

        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun initUI() {
        setCardBackgroundColor()

        setTextHeight()

        setTextWeight()

        setTextAge()
    }

    private fun setCardBackgroundColor() {
        cardMale.setCardBackgroundColor(getCardBackgroundColor(selectedGenderCard == GenderCard.MALE))

        cardFemale.setCardBackgroundColor(getCardBackgroundColor(selectedGenderCard == GenderCard.FEMALE))
    }

    private fun getCardBackgroundColor(isCardSelected: Boolean): Int {
        var colorReference =
            if (isCardSelected) R.color.card_background_selected else R.color.card_background

        return ContextCompat.getColor(this, colorReference)
    }

    private fun setTextHeight() {
        val result = currentHeight.toString()

        textHeight.text = "$result cm"
    }

    private fun setTextWeight() {
        textViewWeight.text = currentWeight.toString()
    }

    private fun setTextAge() {
        textViewAge.text = currentAge.toString()
    }

    private fun calculateBMI() {
        val bmi = currentWeight / ((currentHeight.toDouble() / 100) * (currentHeight.toDouble() / 100))

        val df = DecimalFormat("#.##")

        val result = df.format(bmi).toDouble()

        Log.i("bmi", "result $bmi")
    }
}