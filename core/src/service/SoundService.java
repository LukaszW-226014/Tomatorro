package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Luke on 3/11/2017.
 */

public class SoundService {

    private Sound piggy, jump, bee, pick;

    private Music music;

    public SoundService() {
        init();
    }

    private void init() {
        piggy = Gdx.audio.newSound(Gdx.files.internal("PigSound.mp3"));
        jump = Gdx.audio.newSound(Gdx.files.internal("ClickSound.mp3"));
        bee = Gdx.audio.newSound(Gdx.files.internal("BeeSound.mp3"));
        pick = Gdx.audio.newSound(Gdx.files.internal("PickSound.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("CakeTown.mp3"));

    }

    public void playCoinSound(){
        piggy.play();
    }

    public void playJumpSound(){
        jump.play();
    }

    public void playPickSound(){
        pick.play();
    }

    public void playBeeSound(){
        bee.play();
    }

    public void startPlayingMusic(boolean looped){
        //music.setVolume(0.1f);
        music.play();
        music.setLooping(looped);
    }
}
