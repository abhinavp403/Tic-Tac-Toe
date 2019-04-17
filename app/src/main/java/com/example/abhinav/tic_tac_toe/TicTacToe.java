package com.example.abhinav.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class TicTacToe extends AppCompatActivity implements OnClickListener {

    private MediaPlayer mp;
    private boolean disableSound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            disableSound = bundle.getBoolean("disableSound");
        }
        View newgameButton = findViewById(R.id.new_game_button);
        newgameButton.setOnClickListener(this);
        View helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tictactoe, menu);
        MenuItem toggleSoundItemMenu = menu.findItem(R.id.toogle_sound_menu);
        if(disableSound)
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_off_label);
        else
            toggleSoundItemMenu.setTitle(R.string.toggle_sound_on_label);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toogle_sound_menu:
                if(disableSound){
                    disableSound = false;
                    AlertDialog alert = new AlertDialog.Builder(this).create();
                    alert.setTitle("Sound Toggle Notification");
                    alert.setMessage("Sound is now Enabled");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                    item.setTitle(R.string.toggle_sound_on_label);
                }
                else{
                    disableSound = true;
                    AlertDialog alert = new AlertDialog.Builder(this).create();
                    alert.setTitle("Sound Toggle Notification");
                    alert.setMessage("Sound is now Disabled");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                    item.setTitle(R.string.toggle_sound_off_label);
                }
                return true;
            case R.id.help_menu:
                Intent helpActivity = new Intent(this,Help.class);
                startActivity(helpActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(mp != null)
            mp.release();
        if(!disableSound) {
            mp = MediaPlayer.create(this, R.raw.keypress_standard);
            mp.start();
        }
        switch(v.getId()) {
            case R.id.new_game_button:
                Intent newGameActivity = new Intent(this,GamePlay.class);
                newGameActivity.putExtra("disableSound", disableSound);
                finish();
                startActivity(newGameActivity);
                break;
            case R.id.help_button:
                Intent helpActivity = new Intent(this,Help.class);
                startActivity(helpActivity);
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }
}