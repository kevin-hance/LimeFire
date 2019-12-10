// Kevin Hance and Zach McKee
// Final Project - LimeFire
// SoundsActivity

package com.example.finalprojectmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SoundsActivity extends AppCompatActivity {

    // create global variable for media player
    MediaPlayer mediaPlayer;

    // initialize sound file array to new empty arraylist
    final ArrayList<SoundFile> soundsList = new ArrayList<SoundFile>();
    ArrayAdapter<SoundFile> listAdapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // define toolbar back button behavior
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
        setContentView(R.layout.activity_sounds);

        // create new SoundFile objects for all 48 sounds and add them to soundsList
        // inserting all EDM sounds
        // These sounds were found here, available for free download at
        // https://cymatics.fm/pages/free-download-vault
        soundsList.add(new SoundFile("EDM Clap 1", R.raw.edm_clap1));
        soundsList.add(new SoundFile("EDM Clap 2", R.raw.edm_clap2));
        soundsList.add(new SoundFile("EDM Hat 1", R.raw.edm_hat1));
        soundsList.add(new SoundFile("EDM Hat 2", R.raw.edm_hat2));
        soundsList.add(new SoundFile("EDM Hat 3", R.raw.edm_hat3));
        soundsList.add(new SoundFile("EDM Hat 4", R.raw.edm_hat4));
        soundsList.add(new SoundFile("EDM Hat 5", R.raw.edm_hat5));
        soundsList.add(new SoundFile("EDM Kick 1", R.raw.edm_kick1));
        soundsList.add(new SoundFile("EDM Kick 2", R.raw.edm_kick2));
        soundsList.add(new SoundFile("EDM Percussion 1", R.raw.edm_perc1));
        soundsList.add(new SoundFile("EDM Percussion 2", R.raw.edm_perc2));
        soundsList.add(new SoundFile("EDM Percussion 3", R.raw.edm_perc3));
        soundsList.add(new SoundFile("EDM Percussion 4", R.raw.edm_perc4));
        soundsList.add(new SoundFile("EDM Ride 1", R.raw.edm_ride1));
        soundsList.add(new SoundFile("EDM Snare 1", R.raw.edm_snare1));
        soundsList.add(new SoundFile("EDM Snare 2", R.raw.edm_snare2));

        // inserting all Hip Hop sounds
        // These sounds were found here, available for free download at
        // https://theproducersplug.com/product/kendrick-lamar-damn-drum-kit-free-download/
        soundsList.add(new SoundFile("Hip Hop 808 Bass - Humble", R.raw.hh_808_dna));
        soundsList.add(new SoundFile("Hop Hop 808 Bass - DNA", R.raw.hh_808_humble));
        soundsList.add(new SoundFile("Hop Hop Clap - Crumble", R.raw.hh_clap_crumble));
        soundsList.add(new SoundFile("Hop Hop Clap - Humble", R.raw.hh_clap_humble));
        soundsList.add(new SoundFile("Hop Hop Hat - Maker", R.raw.hh_hat_maker));
        soundsList.add(new SoundFile("Hop Hop Hat - Thundercat", R.raw.hh_hat_thunder));
        soundsList.add(new SoundFile("Hip Hop Kick - Ah Ha", R.raw.hh_kick_ahha));
        soundsList.add(new SoundFile("Hip Hop Kick - Air", R.raw.hh_kick_air));
        soundsList.add(new SoundFile("Hip Hop Kick - Batman", R.raw.hh_kick_batman));
        soundsList.add(new SoundFile("Hip Hop Kick - DNA", R.raw.hh_kick_dna));
        soundsList.add(new SoundFile("Hip Hop Open Hat - Mac", R.raw.hh_oh_mac));
        soundsList.add(new SoundFile("Hip Hop Open Hat - Money", R.raw.hh_oh_money));
        soundsList.add(new SoundFile("Hip Hop Snare - Dre", R.raw.hh_snare_dre));
        soundsList.add(new SoundFile("Hip Hop Snare - Glass", R.raw.hh_snare_glass));
        soundsList.add(new SoundFile("Hip Hop Snare - Hype", R.raw.hh_snare_hype));
        soundsList.add(new SoundFile("Hip Hop Snare - Power", R.raw.hh_snare_power));

        // inserting all Rock sounds
        // These sounds were found here, available for free download at
        // https://www.reddit.com/r/Drumkits/comments/8fzrgi/blaze_7_trap_rock_drum_kit_electronic_and/
        soundsList.add(new SoundFile("Rock 808 Bass 1", R.raw.rock_8081));
        soundsList.add(new SoundFile("Rock 808 Bass 2", R.raw.rock_8082));
        soundsList.add(new SoundFile("Rock Clap 1", R.raw.rock_clap1));
        soundsList.add(new SoundFile("Rock Clap 2", R.raw.rock_clap3));
        soundsList.add(new SoundFile("Rock Crash", R.raw.rock_crash));
        soundsList.add(new SoundFile("Rock Hat 1", R.raw.rock_hat1));
        soundsList.add(new SoundFile("Rock Hat 2", R.raw.rock_hat2));
        soundsList.add(new SoundFile("Rock Kick 1", R.raw.rock_kick1));
        soundsList.add(new SoundFile("Rock Kick 2", R.raw.rock_kick2));
        soundsList.add(new SoundFile("Rock Open Hat", R.raw.rock_openhat));
        soundsList.add(new SoundFile("Rock Percussion", R.raw.rock_perc));
        soundsList.add(new SoundFile("Rock Snap", R.raw.rock_snap));
        soundsList.add(new SoundFile("Rock Snare 1", R.raw.rock_snare1));
        soundsList.add(new SoundFile("Rock Snare 2", R.raw.rock_snare2));
        soundsList.add(new SoundFile("Rock Snare 3", R.raw.rock_snare3));
        soundsList.add(new SoundFile("Rock Triangle", R.raw.rock_triangle));

        // inserting all extra sounds
        // These sounds were found here, available for free download at
        // https://www.reddit.com/r/Drumkits/comments/bzqopc/pharrell_williams_grindin_drum_kit/
        soundsList.add(new SoundFile("Snap", R.raw.snap));
        soundsList.add(new SoundFile("Clap", R.raw.clap));
        soundsList.add(new SoundFile("Stomp", R.raw.stomp));

        final ListView listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<SoundFile>(SoundsActivity.this, android.R.layout.simple_list_item_1, soundsList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // initialize soundId to the sound id of the SoundFile object associated with the
                // list item that the user clicked on
                int soundId = soundsList.get(position).getSoundId();
                mediaPlayer = MediaPlayer.create(SoundsActivity.this, soundId);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        SoundsActivity.this.mediaPlayer = null;
                    }
                });
            }
        });

        // display back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
