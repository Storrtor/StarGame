package stortor.com.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;

public class GameOverMsg extends Sprite {

    private static final float HEIGHT = 0.06f;

    public GameOverMsg(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getTop() - worldBounds.getHalfHeight() * 0.75f);
    }
}
