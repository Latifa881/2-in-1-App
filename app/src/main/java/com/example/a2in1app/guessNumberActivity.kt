package com.example.a2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class guessNumberActivity : AppCompatActivity() {
    lateinit var myRV: RecyclerView
    var answer = 0//make the random number(which will the user guess be global)
    var counter = 3
    var  guessNumArrayList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_number)


        answer = Random.nextInt(0, 11)
        myRV = findViewById(R.id.rvMain)
        myRV.adapter = RecyclerViewAdapter_GuessNumber(guessNumArrayList)
        myRV.layoutManager = LinearLayoutManager(this)

        val addBT = findViewById<Button>(R.id.addBT)

        var guessedNumberET = findViewById<EditText>(R.id.editTextGuess)

        addBT.setOnClickListener {
            var correctGuess = false

            if (counter > 0) {

                val guessedNumber = guessedNumberET.text.toString()
                if (!checkIfEmpty(guessedNumber)) {//check if the edit text is empty or not

                    if (checkGuessCorrect(guessedNumber.toInt())) {
                        guessNumArrayList.add("You got it correctly!!")
                        correctGuess = true
                    } else {
                        counter--
                        guessNumArrayList.add("You guessed $guessedNumber")
                        guessNumArrayList.add("You have $counter guesses left!")
                    }
                    guessedNumberET.setText("")

                    myRV.adapter!!.notifyDataSetChanged()
                }
            }//end of  if (counter >0)//the user has 3 chances to guess the correct number
            var myLayout = findViewById<ConstraintLayout>(R.id.clID)
            if (correctGuess) {

                var rank: String = ""
                when {
                    counter == 3 -> rank = "first"
                    counter == 2 -> rank = "second"
                    counter == 1 -> rank = "third"
                }
                customAlert("Yay!!","Congratulations you got it from $rank time")
                replayGame()
            } else if (counter == 0)//didn't git it
            {
                customAlert("Oops!","Unfortunately you couldn't guess it! The number was $answer")

                replayGame()
            }

        }

    }

    fun checkGuessCorrect(guessedNumber: Int): Boolean {

        if (answer == guessedNumber)
            return true
        return false
    }

    fun checkIfEmpty(str: String): Boolean {
        if (str.isEmpty()) {
            var myLayout = findViewById<ConstraintLayout>(R.id.clID)

            Snackbar.make(myLayout, "Enter a number", Snackbar.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    fun replayGame() {
        guessNumArrayList.clear()
        counter = 3
        answer = Random.nextInt(0, 11)
        myRV.adapter!!.notifyDataSetChanged()
    }
    fun customAlert(title:String,message:String){
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_info)

        //performing positive action
        builder.setPositiveButton("Ok"){dialogInterface, which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(Menu.NONE,2,2, "Main Menu")//add item
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val newGame : MenuItem = menu!!.getItem(0)
        newGame.title="New Game"

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.numbersGame -> {//new game
                var intent= Intent(this,guessNumberActivity::class.java)
                startActivity(intent)

                return true
            }
            R.id.phraseGame -> {//phrase eGame
                var intent= Intent(this,guessPhraseActivity::class.java)
                startActivity(intent)
                return true
            }
          else -> {//main
                var intent= Intent(this,guessPhraseActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}