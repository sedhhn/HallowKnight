package com.HallowKnight.Model;

public class Settings{
    private float musicVolume;
    private boolean musicMuted;
    private float sfxVolume;
    private boolean sfxMuted;

    public Settings(){
        resetSettings();
    }

    public void resetSettings(){
        setMusicVolume(0.7f);
        setMusicMuted(false);
        setSfxVolume(0.7f);
        setSfxMuted(false);
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public boolean isMusicMuted() {
        return musicMuted;
    }

    public void setMusicMuted(boolean musicMuted) {
        this.musicMuted = musicMuted;
    }

    public float getSfxVolume() {
        return sfxVolume;
    }

    public void setSfxVolume(float sfxVolume) {
        this.sfxVolume = sfxVolume;
    }

    public boolean isSfxMuted() {
        return sfxMuted;
    }

    public void setSfxMuted(boolean sfxMuted) {
        this.sfxMuted = sfxMuted;
    }
}
