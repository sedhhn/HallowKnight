package com.HallowKnight.Controller.Managers;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static AudioManager instance;
    private Music currentMusic;
    private String currentMusicPath;
    private Music secondaryMusic;
    private String secondaryMusicPath;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (instance == null) instance = new AudioManager();
        return instance;
    }

    public void playMusic(String path, boolean looping, float volume) {
        if (currentMusicPath != null && currentMusicPath.equals(path)) {
            if (currentMusic != null) {
                currentMusic.setVolume(volume);
                if (!currentMusic.isPlaying()) currentMusic.play();
            }
            return;
        }
        stopMusic();
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        currentMusic.setLooping(looping);
        currentMusic.setVolume(volume);
        currentMusic.play();
        currentMusicPath = path;
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
            currentMusicPath = null;
        }
    }

    public void ensureSecondary(String path, boolean looping) {
        if (secondaryMusicPath != null && secondaryMusicPath.equals(path)) {
            if (secondaryMusic != null && !secondaryMusic.isPlaying()) {
                secondaryMusic.play();
            }
            return;
        }
        stopSecondary();
        secondaryMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        secondaryMusic.setLooping(looping);
        secondaryMusic.setVolume(0);
        secondaryMusic.play();
        secondaryMusicPath = path;
    }

    public void setSecondaryVolume(float volume) {
        if (secondaryMusic != null) {
            secondaryMusic.setVolume(volume);
        }
    }

    public void stopSecondary() {
        if (secondaryMusic != null) {
            secondaryMusic.stop();
            secondaryMusic.dispose();
            secondaryMusic = null;
            secondaryMusicPath = null;
        }
    }

    public void promoteSecondary(float volume) {
        if (secondaryMusic == null) return;
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
        currentMusic = secondaryMusic;
        currentMusicPath = secondaryMusicPath;
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
        secondaryMusic = null;
        secondaryMusicPath = null;
    }

    public String getCurrentMusicPath() { return currentMusicPath; }
    public String getSecondaryMusicPath() { return secondaryMusicPath; }

    public void setMusicVolume(float volume) {
        if (currentMusic != null) currentMusic.setVolume(volume);
    }

    public boolean isMusicPlaying() {
        return currentMusic != null && currentMusic.isPlaying();
    }

    public void pauseMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) currentMusic.pause();
    }

    public void resumeMusic() {
        if (currentMusic != null && !currentMusic.isPlaying()) currentMusic.play();
    }

    public void playSFX(String path) {
        var settings = HallowKnight.hallowKnight.getsettings();
        if (settings.isSfxMuted()) return;
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sound.play(settings.getSfxVolume());
    }

    public void disposeAll() {
        stopMusic();
        stopSecondary();
    }
}
