package stortor.com.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.math.Rect;
import stortor.com.math.Rnd;
import stortor.com.pool.EnemyPool;
import stortor.com.pool.MedicinePool;
import stortor.com.sprite.Medicine;

public class MedicineSpawn {

    private static final float GENERATE_INTERVAL = 20f;
    private static final int HEAL_VALUE = 5;

    private final Vector2 medicineV = new Vector2(0f, -0.3f);

    private final MedicinePool medicinePool;
    private final Rect worldBounds;

    private final TextureRegion[] medicineRegion;

    private float generateTimer;
    private EnemyEmitter enemyEmitter;


    public MedicineSpawn(MedicinePool medicinePool, Rect worldBounds, TextureAtlas atlas, EnemyEmitter enemyEmitter) {
        this.medicinePool = medicinePool;
        this.worldBounds = worldBounds;
        this.enemyEmitter = enemyEmitter;
        medicineRegion = Regions.split(atlas.findRegion("Heal"), 1,1,1);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0f;
            Medicine medicine = medicinePool.obtain();
            medicine.set(medicineRegion, medicineV, HEAL_VALUE * enemyEmitter.getLevel());


            medicine.pos.x = Rnd.nextFloat(worldBounds.getLeft() + medicine.getHalfWidth(),
                    worldBounds.getRight() - medicine.getHalfWidth()
            );
            medicine.setBottom(worldBounds.getTop());
        }

    }

}
