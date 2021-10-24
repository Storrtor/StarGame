package stortor.com.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.BaseScreen;
import stortor.com.math.Rect;
import stortor.com.sprite.Background;
import stortor.com.sprite.SpaceShip;
import stortor.com.sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    private Vector2 v;

    private boolean pressed;
    private int keycodePressed;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star stars[];
    private SpaceShip spaceShip;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        spaceShip = new SpaceShip(atlas);
        v = new Vector2(0.1f, 0f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        spaceShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        bg.dispose();
    }

    // Не смогла инкапсулировать логику управления кораблем в класс корабля, потому что у нас привязка к клавиатуре
    // которая может быть реализована только в класах наследуемых от класса BaseScreen
    // (если я правильно поняла происходящее в коде)
    @Override
    public boolean keyDown(int keycode) {
        pressed = true;
        if (keycode == 21 || keycode == 29) {
            spaceShip.pos.x = spaceShip.pos.x - 0.005f;
        }
        if (keycode == 22 || keycode == 32) {
            spaceShip.pos.x = spaceShip.pos.x + 0.005f;
        }
        keycodePressed = keycode;
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        pressed = false;
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        spaceShip.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        spaceShip.update(delta);
        if (pressed) {
            keyDown(keycodePressed);
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        spaceShip.draw(batch);
        batch.end();
    }

}
