package stortor.com.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;

public class Logo extends Sprite {

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }





}
