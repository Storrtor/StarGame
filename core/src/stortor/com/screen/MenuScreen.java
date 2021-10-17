package stortor.com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 touch;
    private Vector2 v2;
    private Vector2 start;
    private float f;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        start = new Vector2(0,0);
        touch = new Vector2();
        v2 = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, start.x, start.y);
        batch.end();
        if (f > 0) {
            start.add(v2.nor());
            f--;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v2 = touch.cpy().sub(start);
        f = touch.cpy().sub(start).len();
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
