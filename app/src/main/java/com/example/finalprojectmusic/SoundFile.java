// Kevin Hance and Zach McKee
// Final Project - LimeFire
// SoundFile class

package com.example.finalprojectmusic;

import androidx.annotation.NonNull;

public class SoundFile {

    // declare private fields
    private int soundId;
    private String soundName;

    // EVC for sound file
    // DVC is unnecessary as it would never be used
    public SoundFile(String soundName, int soundId) {
        this.soundId = soundId;
        this.soundName = soundName;
    }

    // getter for sound id
    public int getSoundId() {
        return soundId;
    }

    // setter for sound id
    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    // getter for sound name
    public String getSoundName() {
        return soundName;
    }

    // setter for sound name
    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    // toString override as good practice
    @NonNull
    @Override
    public String toString() {
        return soundName;
    }
}
