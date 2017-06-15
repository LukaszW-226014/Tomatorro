package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.wajatos.tomatorro.TomatorroGame;

/**
 * Created by Luke on 3/11/2017.
 */

public class FlyingObject extends Image {

    public enum FlyingObjectType {
        BEE, PASSIVE
    }

    public final static String BEE = "bee.png";
    public final static String PIGGY = "piggybank.png";

    private final static int WIDTH = 70;
    private final static int HEIGHT = 70;
    public final static int STARTING_X_1 = 0;
    public final static int STARTING_X_2 = TomatorroGame.WIDTH;
    public final static int STARTING_Y = -100;
    private int startingX;

    private TomatorroGame game;
    private FlyingObjectType type;


    public FlyingObject(FlyingObjectType type, TomatorroGame game) {
        super(new Texture(getTextTureString(type)));

        this.type = type;

        this.game = game;

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        playSpawnSound();

        //starting position
        startingX = MathUtils.randomBoolean() ? STARTING_X_1 : STARTING_X_2;
        this.setPosition(startingX, STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                reactOnClick();
                FlyingObject.this.remove();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void playSpawnSound() {
        if(FlyingObjectType.PASSIVE.equals(type)){
            game.getSoundService().playCoinSound();
        } else{
            game.getSoundService().playBeeSound();
        }
    }

    private void reactOnClick() {
        if(FlyingObjectType.BEE.equals(type)){
            game.getScoreService().addPoints(50);
        }
        else if(FlyingObjectType.PASSIVE.equals(type)){
            game.getScoreService().addPassiveIncome();
        }

        game.getSoundService().playPickSound();
        FlyingObject.this.remove();
    }

    private static String getTextTureString(FlyingObjectType type) {
        if(FlyingObjectType.BEE.equals(type)){
            return BEE;
        }
        else if(FlyingObjectType.PASSIVE.equals(type)){
            return PIGGY;
        }
        return "";
    }

    public void flyLikeHell(){

        int xSign = 0;

        if(startingX == STARTING_X_1){
            xSign = 1;
        } else{
            xSign = -1;
        }

        int time1 = MathUtils.random(1, 6);
        int time2 = MathUtils.random(1, 6);

        int randomYEffect = MathUtils.random(-100, 500);

        Action a = Actions.parallel(Actions.moveBy(xSign * 300 + (MathUtils.random(-200, 200)), 200 + randomYEffect, time1), Actions.rotateBy(360, time1));

        Action b = Actions.parallel(Actions.moveBy(xSign * -500 + (MathUtils.random(-200, 200)), 900, time2), Actions.rotateBy(360, time2));

        Action c = Actions.run(new Runnable() {
            @Override
            public void run() {
                FlyingObject.this.remove();
            }
        });

        this.addAction(Actions.sequence(a, b, c));
    }
}
