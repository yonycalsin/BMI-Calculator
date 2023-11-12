package com.yonycalsin.bmicaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

enum class GenderCard {
    MALE,
    FEMALE
}

class MainActivity : AppCompatActivity() {
    private lateinit var cardMale: CardView

    private lateinit var cardFemale: CardView

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