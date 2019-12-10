package com.example.finalprojectmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    // Icons made by Good-Ware at https://www.flaticon.com/authors/good-ware

    String selectedKitType;
    String[] kitTypes = getResources().getStringArray(R.array.kitTypes);
    MediaPlayer mediaPlayer = null;
    SeekBar volumeBar;
    AudioManager audioManager;
    GridLayout gridLayout;
    Switch toggleSwitch;
    boolean toggleSwitchBool = true;
    int[][] hipHopSounnds = {{R.raw.hh_808_dna, R.raw.hh_808_humble, R.raw.hh_clap_crumble, R.raw.hh_clap_humble},
                             {R.raw.hh_kick_ahha, R.raw.hh_kick_air, R.raw.hh_kick_batman, R.raw.hh_kick_dna},
                             {R.raw.hh_snare_dre, R.raw.hh_snare_glass, R.raw.hh_snare_hype, R.raw.hh_snare_power},
                             {R.raw.hh_hat_maker, R.raw.hh_hat_thunder, R.raw.hh_oh_mac, R.raw.hh_oh_money}};

    int[][] edmSounds = {{R.raw.edm_ride1, R.raw.edm_clap1, R.raw.edm_clap2, R.raw.edm_hat1},
                         {R.raw.edm_hat2, R.raw.edm_hat3, R.raw.edm_hat4, R.raw.edm_hat5},
                         {R.raw.edm_kick1, R.raw.edm_kick2, R.raw.edm_perc1, R.raw.edm_perc2},
                         {R.raw.edm_perc3, R.raw.edm_perc4, R.raw.edm_snare1, R.raw.edm_snare2}};

    int[][] rockSounds = {{R.raw.rock_8081, R.raw.rock_8082, R.raw.rock_clap1, R.raw.rock_clap3},
                          {R.raw.rock_hat1,  R.raw.rock_hat2, R.raw.rock_crash, R.raw.rock_kick1},
                          {R.raw.rock_kick2,  R.raw.rock_triangle, R.raw.rock_openhat, R.raw.rock_perc},
                          {R.raw.rock_snap, R.raw.rock_snare1, R.raw.rock_snare2, R.raw.rock_snare3}};
    Handler[][] bpmTimers = new Handler[4][4];

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if (bpmTimers[i][j] != null)
                    bpmTimers[i][j].removeCallbacksAndMessages(null);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner spinner = findViewById(R.id.kitSpinner);
        selectedKitType = spinner.getSelectedItem().toString();
        gridLayout = findViewById(R.id.gridLayout);
        toggleSwitch = findViewById(R.id.toggleSwitch);
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleSwitchBool = isChecked;

                if(!isChecked) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                } else {
                    int volumeLevel = volumeBar.getProgress();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeLevel, 0);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKitType = spinner.getSelectedItem().toString();
                Snackbar snackbar = Snackbar.make(gridLayout, "Drums set to " + selectedKitType + " kit", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<String> kitList = new ArrayList<String>();
        kitList.add("Hip-Hop");
        kitList.add("EDM");
        kitList.add("Rock");

        // create an adapter to fuse together the note type string and the spinner
        ArrayAdapter<String> kitTypeAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.activity_list_item,
                android.R.id.text1, // the "main" textview
                kitList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                // for getView, set icon to the corresponding items image resource
                // set text to corresponding items text
                View view = super.getView(position, convertView, parent);
                String kitTypeString = kitList.get(position);

                // default image is the pad - if this shows up, something went wrong
                int imageResource = R.drawable.pad;

                if(position == 0){
                    imageResource = R.drawable.record;
                } else if (position == 1){
                    imageResource = R.drawable.mixer;
                } else if (position == 2) {
                    imageResource = R.drawable.guitar;
                }


                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(kitTypeString);
                ImageView icon = (ImageView) view.findViewById(android.R.id.icon);
                icon.setImageResource(imageResource);
                return view;
            }

            // same functionality as getView for getDrownDownView
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return this.getView(position, convertView, parent);
            }
        };

        //The handler for setting music to repeat at a certain bpm
        View.OnLongClickListener setBpm = new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(final View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Set BPM");
                // prompt user to delete note
                alertDialog.setMessage("What BPM would you like the sound to repeat at? (0 to stop)");

                final EditText bpmEditText = new EditText(MainActivity.this);
                bpmEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertDialog.setView(bpmEditText);

                // if user presses YES, delete note and note title from respective lists and dismiss dialog
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "RETURN TO MPC", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                // if user presses NO, dismiss dialog and take no action
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "SET BPM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String index = v.getTag().toString();
                        int row = Integer.parseInt(index.substring(0,1));
                        int col = Integer.parseInt(index.substring(1));
                        int delay = Integer.parseInt(bpmEditText.getText().toString());
                        setBpmHandler(row, col, delay);
                        dialog.dismiss();
                    }
                });

                // show dialog and notify activity that no further action is needed
                alertDialog.show();
                return true;
            }
        };

        findViewById(R.id.ImageView00).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView10).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView20).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView30).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView01).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView11).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView21).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView31).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView02).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView12).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView22).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView32).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView03).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView13).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView23).setOnLongClickListener(setBpm);
        findViewById(R.id.ImageView33).setOnLongClickListener(setBpm);

        spinner.setAdapter(kitTypeAdapter);
        initControls();
    }

    private void setBpmHandler(final int row, final int col, final int delay)
    {
        final Handler handler = new Handler();
        if(delay > 0) {
            final int msDelay = 60000 / delay;
            if (bpmTimers[row][col] != null)
                bpmTimers[row][col].removeCallbacksAndMessages(null);
            bpmTimers[row][col] = handler;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int soundId = 0;
                    if (selectedKitType.equals(kitTypes[0])) {
                        soundId = hipHopSounnds[row][col];
                    } else if (selectedKitType.equals(kitTypes[1])) {
                        soundId = edmSounds[row][col];
                    } else if (selectedKitType.equals(kitTypes[2])) {
                        soundId = rockSounds[row][col];
                    }

                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, soundId);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            MainActivity.this.mediaPlayer = null;
                        }
                    });
                    handler.postDelayed(this, msDelay);
                }
            });
        }
        else {
            if (bpmTimers[row][col] != null)
                bpmTimers[row][col].removeCallbacksAndMessages(null);
        }
    }
    private void initControls()
    {
        volumeBar = (SeekBar)findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0)
            {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0)
            {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
        });
    }



    public void playSound(View view) {
        int soundId = R.raw.stomp;
        // 0 = hip hop
        // 1 = edm
        // 2 = rock
        String index = view.getTag().toString();
        int row = Integer.parseInt(index.substring(0,1));
        int col = Integer.parseInt(index.substring(1));

        if(selectedKitType.equals(kitTypes[0])){
            soundId = hipHopSounnds[row][col];
        } else if(selectedKitType.equals(kitTypes[1])){
            soundId = edmSounds[row][col];
        } else if(selectedKitType.equals(kitTypes[2])){
            soundId = rockSounds[row][col];
        }

        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                MainActivity.this.mediaPlayer = null;
            }
        });
    }
}
