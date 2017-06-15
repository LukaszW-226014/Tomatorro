package screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

import pl.wajatos.tomatorro.TomatorroGame;

/**
 * Created by Luke on 3/5/2017.
 */

public class SplashScreen extends AbstractScreen{

    private Texture splashImg;

    public SplashScreen(final TomatorroGame game) {
        super(game);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        },2);
    }

    @Override
    protected void init() {
        splashImg = new Texture("splash.png");
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }
}
