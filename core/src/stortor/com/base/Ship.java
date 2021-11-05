package stortor.com.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.math.Rect;
import stortor.com.pool.BulletPool;
import stortor.com.pool.ExplosionPool;
import stortor.com.sprite.Bullet;
import stortor.com.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected ExplosionPool explosionPool;
    protected Sound bulletSound;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected Vector2 v;
    protected Vector2 v0;

    protected float reloadTimer;
    protected float reloadInterval;
    protected Rect worldBounds;

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    private boolean isShot;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletSound.play(0.1f);
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
    }

    public void damage(int hp) {
        this.hp -= hp;
        if (this.hp <= 0) {
            this.hp = 0;
            destroy();
        }
        damageAnimateTimer = 0f;
        frame = 1;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void destroyWithoutBoom() {
        super.destroy();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (getTop() < worldBounds.getTop() && !isShot) {
            reloadTimer = reloadInterval;
            this.isShot = true;
        }
        if (reloadTimer >= reloadInterval) {
            bulletPos.set(pos);
            shoot();
            reloadTimer = 0f;
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.pos, this.getHeight());
    }

}
