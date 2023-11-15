package com.yonycalsin.bmicaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.yonycalsin.bmicaculator.MainActivity.Companion.IMC_RESULT_EXTRA_KEY

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewIMCStatus: TextView

    private lateinit var textViewIMC: TextView

    private lateinit var textViewIMCInfo: TextView

    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val result = intent.extras?.getDouble(IMC_RESULT_EXTRA_KEY) ?: -1.0

        this.initComponents()

        this.initListeners()

        this.initUI(result)
    }

    private fun initComponents() {
        textViewIMCStatus = findViewById(R.id.textViewIMCStatus)

        textViewIMC = findViewById(R.id.textViewIMC)

        textViewIMCInfo = findViewById(R.id.textViewIMCInfo)

        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        textViewIMC.text = result.toString()

        when (result) {
            in 0.00..18.50 -> {
                textViewIMCStatus.text = getString(R.string.imc_status_low_weight)

                textViewIMCInfo.text = getString(R.string.imc_info_low_weight)
            }

            in 18.51..24.99 -> {
                textViewIMCStatus.text = getString(R.string.imc_status_normal_weight)

                textViewIMCInfo.text = getString(R.string.imc_info_normal_weight)
            }

            in 25.00..29.99 -> {
                textViewIMCStatus.text = getString(R.string.imc_status_over_weight)

                textViewIMCInfo.text = getString(R.string.imc_info_over_weight)
            }

            in 30.00..99.00 -> {
                textViewIMCStatus.text = getString(R.string.imc_status_obesity_weight)

                textViewIMCInfo.text = getString(R.string.imc_info_obesity_weight)
            }

            else -> {
                textViewIMCStatus.text = getString(R.string.invalid)

                textViewIMC.text = getString(R.string.invalid)

                textViewIMCInfo.text = getString(R.string.invalid)
            }
        }
    }

}