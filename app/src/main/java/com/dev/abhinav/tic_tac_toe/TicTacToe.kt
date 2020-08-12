package com.dev.abhinav.tic_tac_toe

import android.app.AlertDialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife

class TicTacToe : AppCompatActivity(), View.OnClickListener {
    private var mp: MediaPlayer? = null
    private var disableSound = false

    @BindView(R.id.new_game_button) lateinit var newgameButton: Button
    @BindView(R.id.help_button) lateinit var helpButton: Button
    @BindView(R.id.exit_button) lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        ButterKnife.bind(this)

        volumeControlStream = AudioManager.STREAM_MUSIC
        val bundle = intent.extras
        if (bundle != null) {
            disableSound = bundle.getBoolean("disableSound")
        }

        newgameButton.setOnClickListener(this)
        helpButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tictactoe, menu)
        val toggleSoundItemMenu = menu.findItem(R.id.toogle_sound_menu)
        if (disableSound)
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_off_label)
        else
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_on_label)
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
                val helpActivity =
                    Intent(this, Help::class.java)
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
            R.id.new_game_button -> {
                val newGameActivity = Intent(this, GamePlay::class.java)
                newGameActivity.putExtra("disableSound", disableSound)
                finish()
                startActivity(newGameActivity)
            }
            R.id.help_button -> {
                val helpActivity =
                    Intent(this, Help::class.java)
                startActivity(helpActivity)
            }
            R.id.exit_button -> finish()
        }
    }
}