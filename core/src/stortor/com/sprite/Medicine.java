package stortor.com.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;

public class Medicine extends Sprite {

    private Rect worldBounds;
    private int healValue;
    private final static float HEIGHT = 0.05f;

    private Vector2 v;

    public Medicine(Rect worldBounds) {
        this.worldBounds = worldBounds;
        this.v = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            int healValue
    ) {
        this.regions = regions;
        this.v.set(v);
        this.healValue = healValue;
        setHeightProportion(HEIGHT);
    }

    public int getHealValue() {
        System.out.println(healValue);
        return healValue;
    }
}
