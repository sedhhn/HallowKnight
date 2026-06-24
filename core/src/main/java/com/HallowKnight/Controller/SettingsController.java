package com.HallowKnight.Controller;

import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.HallowKnight;

public class SettingsController {
    HallowKnight game;

    public SettingsController(HallowKnight game){
        this.game=game;
    }

    public void resetSettings(){
        game.getsettings().resetSettings();
    }

    public void setMusicVolume(float volume){
        AudioManager.getInstance().setMusicVolume(volume);
        game.getsettings().setMusicVolume(volume);
    }

    public void setMusicMuted(boolean musicMuted){
        if (musicMuted){
            AudioManager.getInstance().setMusicVolume(0);
        } else{
            AudioManager.getInstance().setMusicVolume(game.getsettings().getMusicVolume());
        }
        game.getsettings().setMusicMuted(musicMuted);
    }

    public void setSfxVolume(float volume){
        game.getsettings().setSfxVolume(volume);
    }

    public void setSfxMuted(boolean sfxMuted){
        game.getsettings().setSfxMuted(sfxMuted);
    }
}
