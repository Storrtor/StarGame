package stortor.com.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.BaseScreen;
import stortor.com.math.Rect;
import stortor.com.sprite.Background;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture img;
    private Vector2 pos;

    private Background background;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background  = new Background(bg);
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0,0);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.draw(img, pos.x, pos.y, 0.5f, 0.5f);
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }



}
