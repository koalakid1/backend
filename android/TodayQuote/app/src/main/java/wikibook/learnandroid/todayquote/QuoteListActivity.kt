package wikibook.learnandroid.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_list_activity)

        val currentQuoteSize = intent.getIntExtra("quote_size", 0)
        Toast.makeText(applicationContext, "현재 ${currentQuoteSize}개의 명언이 저장되어 있습니다.", Toast.LENGTH_SHORT).show()

        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)

        // (1)
        val quotes = Quote.getQuotesFromPreference(pref)

        // (2)
        val layoutManager = LinearLayoutManager(this)

        // (3)
        val adapter = QuoteAdapter(quotes)

        // (4)
        val recyclerView = findViewById<RecyclerView>(R.id.quote_list)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
