package controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import pl.wajatos.tomatorro.TomatorroGame;
import ui.BasicDialog;

/**
 * Created by Luke on 3/16/2017.
 */

public class RandomEventController {

    private static final int RANDOM_TICK_INTERVAL = 5; //TODO change after initial implementation

    private TomatorroGame game;
    private Stage stage;

    public RandomEventController(TomatorroGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        init();
    }

    private void init(){

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(MathUtils.randomBoolean()){
                    triggerRandomEvent();
                }
            }
        },RANDOM_TICK_INTERVAL, RANDOM_TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        int randomNumber = MathUtils.random(1, 3);
        switch (randomNumber){
            case 1:
                moneyEvent();
                break;
            case 2:
                loseMoneyEvent();
                break;
            case 3:
                gainPassiveIncome();
                break;
            default:
                break;
        }
    }

    private void triggerDialog(String text){
        BasicDialog basicDialog = new BasicDialog();
        basicDialog.showDialog(stage, text);
    }

    private void gainPassiveIncome() {
        game.getScoreService().addPassiveIncome();
        triggerDialog("You gained passive income! Yayyy!");
    }

    private void moneyEvent() {
        game.getScoreService().addPoints(123);
        triggerDialog("Free money! Yayyy!");
    }

    private void loseMoneyEvent() {
        game.getScoreService().addPoints(-123);
        triggerDialog("Pay taxes! You tomato!");
    }
}
