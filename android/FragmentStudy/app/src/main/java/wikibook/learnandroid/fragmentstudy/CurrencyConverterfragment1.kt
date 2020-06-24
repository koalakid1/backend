package wikibook.learnandroid.fragmentstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

//(1)
class CurrencyConverterFragment1 : Fragment() {
    // (2)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.currency_converter_fragment1, container, false)

        // (1)
        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val fromCurrencySpinner = view.findViewById<Spinner>(R.id.from_currency)
        val toCurrencySpinner = view.findViewById<Spinner>(R.id.to_currency)

        // (2)
        val currencySelectionArrayAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_item, mutableListOf("USD", "EUR", "JPY", "KRW"))
        currencySelectionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromCurrencySpinner.adapter = currencySelectionArrayAdapter
        toCurrencySpinner.adapter = currencySelectionArrayAdapter

        // (3)
        val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result.text = calculateCurrency(amount.text.toString().toDouble(), fromCurrencySpinner.selectedItem.toString(), toCurrencySpinner.selectedItem.toString()).toString()
            }
        }

        fromCurrencySpinner.onItemSelectedListener = itemSelectedListener
        toCurrencySpinner.onItemSelectedListener = itemSelectedListener

        // (4)
        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(amount.text.toString().toDouble(), fromCurrencySpinner.selectedItem.toString(), toCurrencySpinner.selectedItem.toString()).toString()
        }

        return view
    }


    // (3)
    private val currencyExchangeMap = mapOf("USD" to 1.0, "EUR" to 0.9, "JPY" to 110.0, "KRW" to 1150.0)

    // (4)
    private fun calculateCurrency(amount: Double, from: String, to: String) : Double {
        var USDAmount = if(from != "USD") (amount / currencyExchangeMap[from]!!) else amount

        return currencyExchangeMap[to]!! * USDAmount
    }
}
