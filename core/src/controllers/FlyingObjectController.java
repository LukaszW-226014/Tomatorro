package controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import entities.FlyingObject;
import pl.wajatos.tomatorro.TomatorroGame;

/**
 * Created by Luke on 3/11/2017.
 */

public class FlyingObjectController {

    private int spawnTime ;

    public FlyingObjectController(TomatorroGame game, Stage stage) {
        init(game, stage);
    }

    private void init(final TomatorroGame game, final Stage stage) {
        randomizeSpawnTime();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {

                        addRandomFlyingObjectToStage(game, stage);
                        randomizeSpawnTime();
                    }
                }, spawnTime);

            }
        }, 0, 5);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }

    private void addRandomFlyingObjectToStage(TomatorroGame game, Stage stage){

        FlyingObject flyingObject = null;

        if(MathUtils.randomBoolean()){
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.BEE, game);
        } else{
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.PASSIVE, game);
        }

        stage.addActor(flyingObject);
        flyingObject.flyLikeHell();
    }

}
