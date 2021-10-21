package stortor.com.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;

public class Logo extends Sprite {

    private static final float length = 0.01f;
    private final Vector2 dist;
    private final Vector2 touch;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        dist = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.3f);
    }

    @Override
    public void update(float delta) {
        if(touch.dst(pos) > length) {
            pos.add(dist);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        dist.set(touch.cpy().sub(pos)).setLength(length);
        return super.touchDown(touch, pointer, button);
    }


}
