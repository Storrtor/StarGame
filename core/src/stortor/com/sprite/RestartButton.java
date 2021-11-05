package stortor.com.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stortor.com.base.BaseButton;
import stortor.com.math.Rect;
import stortor.com.screen.GameScreen;

public class RestartButton extends BaseButton {

    private static final float HEIGHT = 0.06f;

    private GameScreen gameScreen;

    public RestartButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getTop() - worldBounds.getHalfHeight() * 1.1f);
    }

    @Override
    public void action() {
        gameScreen.setDefaultProperties();
    }

}
