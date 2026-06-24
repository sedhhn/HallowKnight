package com.HallowKnight.Controller.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager {
    private static AudioManager instance;
    private Music currentMusic;
    private String currentMusicPath;

    private AudioManager(){

    }

    public static AudioManager getInstance(){
        if (instance==null){
            instance=new AudioManager();
        }
        return instance;
    }

    public void playMusic(String path, boolean looping, float volume){
        if (currentMusic!=null && currentMusicPath!=null && currentMusicPath.equals(path)){
            currentMusic.setVolume(volume);
            if (!currentMusic.isPlaying()){
                currentMusic.play();
            }
            return;
        }
        stopMusic();
        currentMusic= Gdx.audio.newMusic(Gdx.files.internal(path));
        currentMusic.setLooping(looping);
        currentMusic.setVolume(volume);
        currentMusic.play();
        currentMusicPath = path;
    }

    public void stopMusic(){
        if (currentMusic!=null){
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic=null;
            currentMusicPath=null;
        }
    }

    public void pauseMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.pause();
        }
    }

    public void resumeMusic() {
        if (currentMusic != null && !currentMusic.isPlaying()) {
            currentMusic.play();
        }
    }

    public void setMusicVolume(float volume) {
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
    }

    public boolean isMusicPlaying() {
        return currentMusic != null && currentMusic.isPlaying();
    }
}
