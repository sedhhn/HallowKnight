package com.HallowKnight.Model;

public class Settings{
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;

    private float musicVolume;
    private boolean musicMuted;
    private float sfxVolume;
    private boolean sfxMuted;
    private int gameResolutionWidth;
    private int gameResolutionHeight;
    private boolean fullscreen;

    public Settings(){
        resetSettings();
    }

    public void resetSettings(){
        setMusicVolume(0.7f);
        setMusicMuted(false);
        setSfxVolume(0.7f);
        setSfxMuted(false);
        setGameResolutionWidth(DEFAULT_WIDTH);
        setGameResolutionHeight(DEFAULT_HEIGHT);
        setFullscreen(false);
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

    public int getGameResolutionWidth() {
        return gameResolutionWidth;
    }

    public void setGameResolutionWidth(int gameResolutionWidth) {
        this.gameResolutionWidth = gameResolutionWidth;
    }

    public int getGameResolutionHeight() {
        return gameResolutionHeight;
    }

    public void setGameResolutionHeight(int gameResolutionHeight) {
        this.gameResolutionHeight = gameResolutionHeight;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }
}
