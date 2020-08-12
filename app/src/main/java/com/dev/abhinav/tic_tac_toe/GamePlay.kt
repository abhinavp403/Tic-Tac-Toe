package com.dev.abhinav.tic_tac_toe

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife

class GamePlay : AppCompatActivity(), View.OnClickListener {
    private var counter = 0
    private var position = 0
    private val gameContext: Context = this
    private var mp: MediaPlayer? = null
    private var gameOver = false
    private var disableSound = false

    @BindView(R.id.game_play_back_button) lateinit var backButton: Button
    @BindView(R.id.game_play_restart_button) lateinit var restartButton: Button
    @BindView(R.id.user_option_button_1_1) lateinit var user11Button: Button
    @BindView(R.id.user_option_button_1_2) lateinit var user12Button: Button
    @BindView(R.id.user_option_button_1_3) lateinit var user13Button: Button
    @BindView(R.id.user_option_button_2_1) lateinit var user21Button: Button
    @BindView(R.id.user_option_button_2_2) lateinit var user22Button: Button
    @BindView(R.id.user_option_button_2_3) lateinit var user23Button: Button
    @BindView(R.id.user_option_button_3_1) lateinit var user31Button: Button
    @BindView(R.id.user_option_button_3_2) lateinit var user32Button: Button
    @BindView(R.id.user_option_button_3_3) lateinit var user33Button: Button
    @BindView(R.id.textView_player1) lateinit var player1Label: TextView
    @BindView(R.id.textView_player2) lateinit var player2Label: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)
        ButterKnife.bind(this)

        volumeControlStream = AudioManager.STREAM_MUSIC
        val bundle = intent.extras
        if (bundle != null) {
            disableSound = bundle.getBoolean("disableSound")
        }

        backButton.setOnClickListener(this)
        restartButton.setOnClickListener(this)
        user11Button.setOnClickListener(this)
        user12Button.setOnClickListener(this)
        user13Button.setOnClickListener(this)
        user21Button.setOnClickListener(this)
        user22Button.setOnClickListener(this)
        user23Button.setOnClickListener(this)
        user31Button.setOnClickListener(this)
        user32Button.setOnClickListener(this)
        user33Button.setOnClickListener(this)
        player1Label.setOnClickListener(this)
        player2Label.setOnClickListener(this)
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean("disableSound", disableSound)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        disableSound = savedInstanceState.getBoolean("disableSound")
//        val menu = findViewById<View>(R.menu.tictactoe) as Menu
//        val toggleSoundItemMenu = menu.findItem(R.id.toogle_sound_menu)
//        if (disableSound)
//        toggleSoundItemMenu.setTitle(R.string.toggle_sound_off_label)
//        else toggleSoundItemMenu.setTitle(R.string.toggle_sound_on_label)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tictactoe, menu)
        val toggleSoundItemMenu = menu.findItem(R.id.toogle_sound_menu)
        if (disableSound) toggleSoundItemMenu.setTitle(R.string.toggle_sound_off_label) else toggleSoundItemMenu.setTitle(
            R.string.toggle_sound_on_label
        )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toogle_sound_menu -> {
                if (disableSound) {
                    disableSound = false
                    val alert = AlertDialog.Builder(this).create()
                    alert.setTitle("Sound Toggle Notification")
                    alert.setMessage("Sound is now Enabled")
                    alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {
                            dialog, _ -> dialog.cancel()
                    }
                    alert.show()
                    item.setTitle(R.string.toggle_sound_on_label)
                } else {
                    disableSound = true
                    val alert = AlertDialog.Builder(this).create()
                    alert.setTitle("Sound Toggle Notification")
                    alert.setMessage("Sound is now Disabled")
                    alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {
                            dialog, _ -> dialog.cancel()
                    }
                    alert.show()
                    item.setTitle(R.string.toggle_sound_off_label)
                }
                true
            }
            R.id.help_menu -> {
                val helpActivity = Intent(this, Help::class.java)
                startActivity(helpActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View) {
        if (mp != null) mp!!.release()
        if (!disableSound) {
            mp = MediaPlayer.create(this, R.raw.keypress_standard)
            mp!!.start()
        }
        when (v.id) {
            R.id.user_option_button_1_1 -> {
                val b_1_1 = v as Button
                setXAndO(b_1_1)
                position = 1
            }
            R.id.user_option_button_1_2 -> {
                val b_1_2 = v as Button
                setXAndO(b_1_2)
                position = 2
            }
            R.id.user_option_button_1_3 -> {
                val b_1_3 = v as Button
                setXAndO(b_1_3)
                position = 3
            }
            R.id.user_option_button_2_1 -> {
                val b_2_1 = v as Button
                setXAndO(b_2_1)
                position = 4
            }
            R.id.user_option_button_2_2 -> {
                val b_2_2 = v as Button
                setXAndO(b_2_2)
                position = 5
            }
            R.id.user_option_button_2_3 -> {
                val b_2_3 = v as Button
                setXAndO(b_2_3)
                position = 6
            }
            R.id.user_option_button_3_1 -> {
                val b_3_1 = v as Button
                setXAndO(b_3_1)
                position = 7
            }
            R.id.user_option_button_3_2 -> {
                val b_3_2 = v as Button
                setXAndO(b_3_2)
                position = 8
            }
            R.id.user_option_button_3_3 -> {
                val b_3_3 = v as Button
                setXAndO(b_3_3)
                position = 9
            }
            R.id.game_play_back_button -> {
                val mainScreenActivity = Intent(this, TicTacToe::class.java)
                mainScreenActivity.putExtra("disableSound", disableSound)
                finish()
                startActivity(mainScreenActivity)
            }
            R.id.game_play_restart_button -> resetGame()
            R.id.textView_player1 -> {
                val textViewPlayer1 = v as TextView
                setPlayerName(textViewPlayer1)
            }
            R.id.textView_player2 -> {
                val textViewPlayer2 = v as TextView
                setPlayerName(textViewPlayer2)
            }
        }
        if (counter >= 5 && isGameOver(position) && !gameOver) {
            gameOver = true
            val alertDialogBuilder = AlertDialog.Builder(gameContext)
            alertDialogBuilder.setTitle("Game Over")
            val player1Name = (findViewById<View>(R.id.textView_player1) as TextView).text.toString()
            val player2Name = (findViewById<View>(R.id.textView_player2) as TextView).text.toString()
            if (counter % 2 == 0) {
                alertDialogBuilder
                    .setMessage("$player2Name Won the Game! \n Restart Game?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { _, _ -> // if this button is clicked, close
                        // current activity
                        resetGame()
                    }
                    .setNegativeButton("No") { dialog, _ -> // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel()
                    }
                //Player 2 won the game
            } else {
                alertDialogBuilder
                    .setMessage("$player1Name Won the Game! \n Restart Game?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { _, _ -> // if this button is clicked, close
                        // current activity
                        resetGame()
                    }
                    .setNegativeButton("No") { dialog, _ -> // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel()
                    }
                //Player 1 won the game
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } else if (counter == 9 && !gameOver) {
            gameOver = true
            val alertDialogBuilder =
                AlertDialog.Builder(gameContext)
            alertDialogBuilder.setTitle("Game Over")
            alertDialogBuilder
                .setMessage("Game Resulted in a Draw! \nRestart Game?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ -> // if this button is clicked, close
                    // current activity
                    resetGame()
                }
                .setNegativeButton("No") { dialog, _ -> // if this button is clicked, just close
                    // the dialog box and do nothing
                    dialog.cancel()
                }
            //Draw
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun setXAndO(b: Button) {
        if (b.text.toString().isEmpty()) {
            if (counter % 2 == 0) b.text = "X" else b.text = "O"
            counter++
        }
    }

    private fun setPlayerName(textView: TextView) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Title")
        alert.setMessage("Enter Player Name")
        val inputText = EditText(this)
        alert.setView(inputText)
        alert.setPositiveButton("Ok") { _, _ ->
            val value = inputText.text.toString()
            if (value.length > 0) {
                textView.text = value
            }
        }
        alert.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        alert.show()
    }

    private fun isGameOver(position: Int): Boolean {
        val data11 = (findViewById<View>(R.id.user_option_button_1_1) as Button).text.toString()
        val data12 = (findViewById<View>(R.id.user_option_button_1_2) as Button).text.toString()
        val data13 = (findViewById<View>(R.id.user_option_button_1_3) as Button).text.toString()
        val data21 = (findViewById<View>(R.id.user_option_button_2_1) as Button).text.toString()
        val data22 = (findViewById<View>(R.id.user_option_button_2_2) as Button).text.toString()
        val data23 = (findViewById<View>(R.id.user_option_button_2_3) as Button).text.toString()
        val data31 = (findViewById<View>(R.id.user_option_button_3_1) as Button).text.toString()
        val data32 = (findViewById<View>(R.id.user_option_button_3_2) as Button).text.toString()
        val data33 = (findViewById<View>(R.id.user_option_button_3_3) as Button).text.toString()
        when (position) {
            1 -> {
                if (data11 == data12 && data11 == data13)
                    return true
                if (data11 == data21 && data11 == data31)
                    return true
                if (data11 == data22 && data11 == data33)
                    return true
            }
            2 -> {
                if (data12 == data11 && data12 == data13)
                    return true
                if (data12 == data22 && data12 == data32)
                    return true
            }
            3 -> {
                if (data13 == data11 && data13 == data12)
                    return true
                if (data13 == data22 && data13 == data31)
                    return true
                if (data13 == data23 && data13 == data33)
                    return true
            }
            4 -> {
                if (data21 == data11 && data21 == data31)
                    return true
                if (data21 == data22 && data21 == data23)
                    return true
            }
            5 -> {
                if (data22 == data12 && data22 == data32)
                    return true
                if (data22 == data21 && data22 == data23)
                    return true
                if (data22 == data11 && data22 == data33)
                    return true
                if (data22 == data13 && data22 == data31)
                    return true
            }
            6 -> {
                if (data23 == data13 && data23 == data33)
                    return true
                if (data23 == data21 && data23 == data22)
                    return true
            }
            7 -> {
                if (data31 == data11 && data31 == data21)
                    return true
                if (data31 == data32 && data31 == data33)
                    return true
                if (data31 == data22 && data31 == data13)
                    return true
            }
            8 -> {
                if (data32 == data12 && data32 == data22)
                    return true
                if (data32 == data31 && data32 == data33)
                    return true
            }
            9 -> {
                if (data33 == data11 && data33 == data22)
                    return true
                if (data33 == data13 && data33 == data23)
                    return true
                if (data33 == data31 && data33 == data32)
                    return true
            }
        }
        return false
    }

    private fun resetGame() {
        counter = 0
        gameOver = false
        (findViewById<View>(R.id.user_option_button_1_1) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_1_2) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_1_3) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_2_1) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_2_2) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_2_3) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_3_1) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_3_2) as Button).text = ""
        (findViewById<View>(R.id.user_option_button_3_3) as Button).text = ""
    }
}