package id.qibee.catatanhutang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val utangViewModel: UtangViewModel by viewModels {
        WordViewModelFactory((application as UtangApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvUtang = findViewById<RecyclerView>(R.id.utangRv)
        val adapter = UtangRvAdapter()
        rvUtang.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fabTambahCatatan)
        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, NewUtangActivity::class.java))
        }
        utangViewModel.allUtangs.observe(this) {
            it.let {
                adapter.submitList(it)
                Log.d("Utangs", " --> $it ")
            }
        }
    }
}