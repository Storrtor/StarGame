package stortor.com.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stortor.com.base.SpritesPool;
import stortor.com.math.Rect;
import stortor.com.sprite.Medicine;

public class MedicinePool extends SpritesPool<Medicine> {

    private final Rect worldBounds;

    public MedicinePool(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    protected Medicine newObject() {
        return new Medicine(worldBounds);
    }

}
