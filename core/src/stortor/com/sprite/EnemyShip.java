package stortor.com.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.Ship;
import stortor.com.math.Rect;
import stortor.com.pool.BulletPool;
import stortor.com.pool.ExplosionPool;

public class EnemyShip extends Ship {

    private Vector2 startV;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.bulletSound = bulletSound;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.startV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
        if (getTop() > worldBounds.getTop()) {
            this.v.set(0f, -0.7f);
        } else if (getTop() < worldBounds.getTop()) {
            this.v.set(startV);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            float height
    ) {
        this.regions = regions;
        this.v.set(v);
        this.startV.set(v);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.damage = damage;
        this.hp = hp;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
    }

    public boolean isBulletCollision(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }
}


