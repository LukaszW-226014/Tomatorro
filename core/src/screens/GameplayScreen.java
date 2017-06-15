package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import controllers.FlyingObjectController;
import controllers.RandomEventController;
import entities.Player;
import pl.wajatos.tomatorro.TomatorroGame;
import service.PassiveIncomeService;
import ui.BasicDialog;
import ui.IClickCallback;
import ui.PlayerButton;
import ui.ResetScoreButton;
import ui.GameLabel;

public class GameplayScreen extends AbstractScreen{

    private Image bgImg;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private GameLabel gameLabel;
    private FlyingObjectController flyingObjectController;
    private RandomEventController randomEventController;
    private PassiveIncomeService passiveIncomeService;

    public GameplayScreen(TomatorroGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initBg();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
        initFlyingStuffController();
        startTheMusic();
        initPassiveIncomeService();
        initPassiveIncomeInfoDialog();
        initRandomEventController();
    }

    private void initRandomEventController() {
        randomEventController = new RandomEventController(game, stage);
    }

    private void initPassiveIncomeInfoDialog() {
        if(passiveIncomeService.getPointsToAdd() > 0){
            BasicDialog basicDialog = new BasicDialog();
            stage.addActor(basicDialog);
            basicDialog.showDialog(stage, "Passive income gained: " + passiveIncomeService.getPointsToAdd());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGamestate();
    }

    private void update() {
        gameLabel.setText("Score: " + game.getScoreService().getPoints());
        stage.act();
    }

    private void initPassiveIncomeService() {
        passiveIncomeService = new PassiveIncomeService(game.getScoreService());
        passiveIncomeService.start();
    }

    private void startTheMusic() {
        game.getSoundService().startPlayingMusic(true);
    }

    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("bg.jpg"));
        stage.addActor(bgImg);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().resetGameScore();
            }
        });

        stage.addActor(resetScoreButton);
    }

    private void initScoreLabel() {
        gameLabel = new GameLabel();
        stage.addActor(gameLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
                game.getSoundService().playJumpSound();
            }
        });

        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

}
