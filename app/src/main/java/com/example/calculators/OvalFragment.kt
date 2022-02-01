package com.example.calculators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.calculators.databinding.FragmentOvalBinding
class OvalFragment : Fragment(R.layout.fragment_oval) {
    private var _binding: FragmentOvalBinding? = null
    private val binding get() = _binding!!
    private var strDisplay = ""
    private var strAnswer = ""
    private var utility = Utility()
    private var operator: Char? = null
    private var firstValue: Double? = null
    private var secondValue: Double? = null
    private var afterEqual = false
    private var input = ""
    private val TAG = "CircleFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOvalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check(buttons)
        binding.btn0.setOnClickListener { clickEvent(it) }
        binding.btn1.setOnClickListener { clickEvent(it) }
        binding.btn2.setOnClickListener { clickEvent(it) }
        binding.btn3.setOnClickListener { clickEvent(it) }
        binding.btn4.setOnClickListener { clickEvent(it) }
        binding.btn5.setOnClickListener { clickEvent(it) }
        binding.btn6.setOnClickListener { clickEvent(it) }
        binding.btn7.setOnClickListener { clickEvent(it) }
        binding.btn8.setOnClickListener { clickEvent(it) }
        binding.btn9.setOnClickListener { clickEvent(it) }
        binding.btnPoint.setOnClickListener { clickEvent(it) }
        binding.btnPlus.setOnClickListener { operatorEvent(it) }
        binding.btnMinus.setOnClickListener { operatorEvent(it) }
        binding.btnPow.setOnClickListener { operatorEvent(it) }
        binding.btnAC.setOnClickListener { operatorEvent(it) }
        binding.btnDivision.setOnClickListener { operatorEvent(it) }
        binding.btnEquals.setOnClickListener { operatorEvent(it) }
        binding.btnMultiply.setOnClickListener { operatorEvent(it) }
        binding.btnPercentage.setOnClickListener { operatorEvent(it) }

    }

    private fun clickEvent(view: View) {
        if (afterEqual)
            beginning()
        when (view as Button) {
            binding.btn0 ->  strDisplay +=("0")
            binding.btn1 ->  strDisplay +=("1")
            binding.btn2 ->  strDisplay +=("2")
            binding.btn3 ->  strDisplay +=("3")
            binding.btn4 ->  strDisplay +=("4")
            binding.btn5 ->  strDisplay +=("5")
            binding.btn6 ->  strDisplay +=("6")
            binding.btn7 ->  strDisplay +=("7")
            binding.btn8 ->  strDisplay +=("8")
            binding.btn9 ->  strDisplay +=("9")
            binding.btnPoint ->  strDisplay +=(".")
        }
        binding.tvDisplay.text = strDisplay
    }

    private fun operatorEvent(view: View) {
        afterEqual = false

        when (view as Button) {
            binding.btnEquals -> equalOperation()
            binding.btnPlus -> operation('+')
            binding.btnMinus -> operation('-')
            binding.btnDivision -> operation('/')
            binding.btnMultiply -> operation('*')
            binding.btnPow-> operation('^')

            binding.btnPercentage -> {
                strDisplay = binding.tvDisplay.text.toString()
                if (strDisplay.isNullOrEmpty()) return
                strDisplay = equal(strDisplay.toDouble(), 100.00, '/')
                binding.tvDisplay.text = strDisplay
            }
            binding.btnAC -> {
                beginning()
                binding.tvDisplay.text = strDisplay
            }
            binding.btnPow-> operation('^')


        }
    }

    private fun beginning() {
        utility = Utility()
        strDisplay = ""
        strAnswer = ""
        input = ""
        firstValue = null
        secondValue = null
        operator = null
        afterEqual = false
    }

    private fun operation(currentOperator: Char) {
        try {
            if (strDisplay == "") {
                operator = currentOperator
                return
            }
            if (firstValue == null) {
                firstValue = strDisplay.toDouble()
                operator = currentOperator
                binding.tvDisplay.text = checkLongOrDouble(firstValue!!)
                strDisplay = ""
            } else {
                secondValue = strDisplay.toDouble()
                strAnswer = equal(firstValue!!, secondValue!!, operator!!)
                binding.tvDisplay.text = strAnswer
                firstValue = strAnswer.toDouble()
                operator = currentOperator
                strDisplay = ""
            }
        } catch (e: Exception) {
            binding.tvDisplay.text = "Error"
            beginning()
        }


    }


    private fun equalOperation() {
        try {

            if (operator == null) {
                return
            }
            afterEqual = true
            if (secondValue == null) {
                if (strDisplay.isNullOrEmpty())
                    secondValue = firstValue
                else
                    secondValue = strDisplay.toDouble()
                strAnswer = equal(firstValue!!, secondValue!!, operator!!)
                binding.tvDisplay.text = strAnswer
                firstValue = strAnswer.toDouble()
                strDisplay = ""

            } else {
                if (strDisplay.isNullOrEmpty())
                    strAnswer = equal(firstValue!!, secondValue!!, operator!!)
                else
                    secondValue = strDisplay.toDouble()
                strAnswer = equal(firstValue!!, secondValue!!, operator!!)
                binding.tvDisplay.text = strAnswer
                firstValue = strAnswer.toDouble()
                strDisplay = ""

            }
        } catch (e: Exception) {
            binding.tvDisplay.text = "Error"
            beginning()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}