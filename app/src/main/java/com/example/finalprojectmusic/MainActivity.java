package com.example.finalprojectmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Icons made by Good-Ware at https://www.flaticon.com/authors/good-ware

    String selectedKitType;
    String[] kitTypes;
    MediaPlayer mediaPlayer = null;
    SeekBar volumeBar;
    AudioManager audioManager;
    GridLayout gridLayout;
    Switch toggleSwitch;
    boolean toggleSwitchBool = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kitTypes = getResources().getStringArray(R.array.kitTypes);
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

        spinner.setAdapter(kitTypeAdapter);

        initControls();
    }

    private void initControls()
    {
        try
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
        catch (Exception e) // TODO find out which exception is thrown
        {
            e.printStackTrace();
        }
    }



    public void playSound(View view) {
        int soundId = R.raw.stomp;
        int kitType = -1;
        // 0 = hip hop
        // 1 = edm
        // 2 = rock

        if(selectedKitType.equals(kitTypes[0])){
            kitType = 0;
        } else if(selectedKitType.equals(kitTypes[1])){
            kitType = 1;
        } else if(selectedKitType.equals(kitTypes[2])){
            kitType = 2;
        }

        // if kit type is hip hop
        if(kitType == 0) {
            switch (view.getId()) {
                case R.id.ImageView00:
                    soundId = R.raw.hh_808_dna;
                    break;
                case R.id.ImageView10:
                    soundId = R.raw.hh_808_humble;
                    break;
                case R.id.ImageView20:
                    soundId = R.raw.hh_clap_crumble;
                    break;
                case R.id.ImageView30:
                    soundId = R.raw.hh_clap_humble;
                    break;
                case R.id.ImageView01:
                    soundId = R.raw.hh_kick_ahha;
                    break;
                case R.id.ImageView11:
                    soundId = R.raw.hh_kick_air;
                    break;
                case R.id.ImageView21:
                    soundId = R.raw.hh_kick_batman;
                    break;
                case R.id.ImageView31:
                    soundId = R.raw.hh_kick_dna;
                    break;
                case R.id.ImageView02:
                    soundId = R.raw.hh_snare_dre;
                    break;
                case R.id.ImageView12:
                    soundId = R.raw.hh_snare_glass;
                    break;
                case R.id.ImageView22:
                    soundId = R.raw.hh_snare_hype;
                    break;
                case R.id.ImageView32:
                    soundId = R.raw.hh_snare_power;
                    break;
                case R.id.ImageView03:
                    soundId = R.raw.hh_hat_maker;
                    break;
                case R.id.ImageView13:
                    soundId = R.raw.hh_hat_thunder;
                    break;
                case R.id.ImageView23:
                    soundId = R.raw.hh_oh_mac;
                    break;
                case R.id.ImageView33:
                    soundId = R.raw.hh_oh_money;
                    break;

                default:
                    soundId = R.raw.stomp;
            }
        } else if(kitType == 1){
                switch (view.getId()){
                    case R.id.ImageView00:
                        soundId = R.raw.edm_ride1;
                        break;
                    case R.id.ImageView10:
                        soundId = R.raw.edm_clap1;
                        break;
                    case R.id.ImageView20:
                        soundId = R.raw.edm_clap2;
                        break;
                    case R.id.ImageView30:
                        soundId = R.raw.edm_hat1;
                        break;
                    case R.id.ImageView01:
                        soundId = R.raw.edm_hat2;
                        break;
                    case R.id.ImageView11:
                        soundId = R.raw.edm_hat3;
                        break;
                    case R.id.ImageView21:
                        soundId = R.raw.edm_hat4;
                        break;
                    case R.id.ImageView31:
                        soundId = R.raw.edm_hat5;
                        break;
                    case R.id.ImageView02:
                        soundId = R.raw.edm_kick1;
                        break;
                    case R.id.ImageView12:
                        soundId = R.raw.edm_kick2;
                        break;
                    case R.id.ImageView22:
                        soundId = R.raw.edm_perc1;
                        break;
                    case R.id.ImageView32:
                        soundId = R.raw.edm_perc2;
                        break;
                    case R.id.ImageView03:
                        soundId = R.raw.edm_perc3;
                        break;
                    case R.id.ImageView13:
                        soundId = R.raw.edm_perc4;
                        break;
                    case R.id.ImageView23:
                        soundId = R.raw.edm_snare1;
                        break;
                    case R.id.ImageView33:
                        soundId = R.raw.edm_snare2;
                        break;
                    default:
                        soundId = R.raw.stomp;
                }
        } else if(kitType == 2){
            switch (view.getId()){
                case R.id.ImageView00:
                    soundId = R.raw.rock_8081;
                    break;
                case R.id.ImageView10:
                    soundId = R.raw.rock_8082;
                    break;
                case R.id.ImageView20:
                    soundId = R.raw.rock_clap1;
                    break;
                case R.id.ImageView30:
                    soundId = R.raw.rock_clap3;
                    break;
                case R.id.ImageView01:
                    soundId = R.raw.rock_hat1;
                    break;
                case R.id.ImageView11:
                    soundId = R.raw.rock_hat2;
                    break;
                case R.id.ImageView21:
                    soundId = R.raw.rock_crash;
                    break;
                case R.id.ImageView31:
                    soundId = R.raw.rock_kick1;
                    break;
                case R.id.ImageView02:
                    soundId = R.raw.rock_kick2;
                    break;
                case R.id.ImageView12:
                    soundId = R.raw.rock_triangle;
                    break;
                case R.id.ImageView22:
                    soundId = R.raw.rock_openhat;
                    break;
                case R.id.ImageView32:
                    soundId = R.raw.rock_perc;
                    break;
                case R.id.ImageView03:
                    soundId = R.raw.rock_snap;
                    break;
                case R.id.ImageView13:
                    soundId = R.raw.rock_snare1;
                    break;
                case R.id.ImageView23:
                    soundId = R.raw.rock_snare2;
                    break;
                case R.id.ImageView33:
                    soundId = R.raw.rock_snare3;
                    break;
                default:
                    soundId = R.raw.stomp;
            }
        }
        mediaPlayer = MediaPlayer.create(this, soundId);
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
