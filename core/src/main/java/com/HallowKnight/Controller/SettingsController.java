package com.HallowKnight.Controller;

import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Gdx;

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

    public void setResolution(int width, int height){
        game.getsettings().setGameResolutionWidth(width);
        game.getsettings().setGameResolutionHeight(height);
        if (!game.getsettings().isFullscreen()){
            Gdx.graphics.setWindowedMode(width, height);
        }
    }

    public void setFullscreen(boolean fullscreen){
        game.getsettings().setFullscreen(fullscreen);
        if (fullscreen){
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            int w = game.getsettings().getGameResolutionWidth();
            int h = game.getsettings().getGameResolutionHeight();
            Gdx.graphics.setWindowedMode(w, h);
        }
    }
}
