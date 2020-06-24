package wikibook.learnandroid.todayquote

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class QuoteAdapter(private val dataList: List<Quote>) : RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>() {
    // (2)
    class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var quote : Quote
        val quoteText = view.findViewById<TextView>(R.id.quote_text)
        val quoteFrom = view.findViewById<TextView>(R.id.quote_from)

        fun bind(q : Quote) {
            this.quote = q

            quoteText.text = quote.text
            quoteFrom.text = quote.from
        }
    }

    // (3)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        // (4)
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    // (5)
    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    // (6)
    override fun getItemCount() = dataList.size

    // (7)
    override fun getItemViewType(position: Int) = R.layout.quote_list_item
}
