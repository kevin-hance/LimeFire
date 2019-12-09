package com.example.finalprojectmusic;

import androidx.annotation.NonNull;

public class SoundFile {
    private int soundId;
    private String soundName;

    public SoundFile(String soundName, int soundId) {
        this.soundId = soundId;
        this.soundName = soundName;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    @NonNull
    @Override
    public String toString() {
        return soundName;
    }
}
