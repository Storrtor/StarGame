package stortor.com.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;
import stortor.com.math.Rnd;

public class Star extends Sprite {

    //движение звезды, вектор скорости
    private final Vector2 v;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2(Rnd.nextFloat(-0.005f, 0.0005f), Rnd.nextFloat(-0.2f, -0.1f)); //летит только вниз, поэтому y < 0
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float height = v.y * 0.07f;
        //размер звезды и скорость падения
        setHeightProportion(height);
        // место появления:
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
    }

    private void checkBounds() {
        if(getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if(getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if(getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
    }

}
