package com.yonycalsin.bmicaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
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

    private var selectedGenderCard: Enum<GenderCard> = GenderCard.MALE

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

            val result = DecimalFormat("#.##").format(value)

            textHeight.text = "$result cm"
        }
    }

    private fun initUI() {
        setCardBackgroundColor()
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
}