package com.example.a2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var guessNumberBT: Button
    lateinit var guessPhraseBT:Button
    lateinit var linearLayoutID:LinearLayout
    fun changeActivity(code:Int){
        if(code==0){
            var intent=Intent(this,guessNumberActivity::class.java)
            startActivity(intent)
        }else{
            var intent=Intent(this,guessPhraseActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        guessNumberBT=findViewById(R.id.guessNumberBT)
        guessPhraseBT=findViewById(R.id.guessPhraseBT)
        linearLayoutID=findViewById(R.id.linearLayoutID)
        guessNumberBT.setOnClickListener{
          //  Toast.makeText(this, "guessNumberBT!", Toast.LENGTH_LONG).show()
          changeActivity(0)
        }
        guessPhraseBT.setOnClickListener{
          //  Toast.makeText(this, "guessPhraseBT!", Toast.LENGTH_LONG).show()
           changeActivity(1)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.numbersGame -> {
                changeActivity(0)
                return true
            }
            R.id.phraseGame -> {
                changeActivity(1)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}