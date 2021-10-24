package stortor.com.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Sprite;
import stortor.com.math.Rect;

public class SpaceShip extends Sprite {

    private final Vector2 touch;
    private Vector2 v;
    private Rect worldBounds;

    private static final float PADDING = 0.03f; // отступ
    private static final float PADDING_FOR_CHECK_BOUNDS = 0.1f; // отступ от краев ограничения движения корабля за пределы

    public SpaceShip(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 200, 300));
        touch = new Vector2();
        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        checkBounds();
        if (touch.x > this.pos.x) {
            pos.add(v);
        }
        if (touch.x < this.pos.x){
            pos.sub(v);
        }
    }

    // Вот тут еще проблема. Запоминает touch и если сначала попробовать поуправлять кораблем с мыши
    // А затем с клавы, то корабль бесконечно стремится к этому touch, мешая управлению с клавы
    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        v = new Vector2(0.004f, 0f);
        this.touch.set(touch);
        return super.touchDown(touch, pointer, button);
    }

    // Ограничение движения корабля. Только проблема в том, что он как бы отстает от краев из-за такого условия.
    // Но по-другому придумать не смогла
    private void checkBounds() {
        if(this.pos.x < worldBounds.getLeft() + PADDING_FOR_CHECK_BOUNDS) {
            this.pos.x = worldBounds.getLeft() + PADDING_FOR_CHECK_BOUNDS;
        }
        if(this.pos.x > worldBounds.getRight() - PADDING_FOR_CHECK_BOUNDS) {
            this.pos.x = worldBounds.getRight() - PADDING_FOR_CHECK_BOUNDS;
        }
    }

}
