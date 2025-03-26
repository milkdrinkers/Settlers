package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;


public class SettlerPushEvent extends AbstractCancellableSettlerEvent {
    private final Vector vector;
    private final Entity entity;

    public SettlerPushEvent(AbstractSettler settler, Vector vector, Entity entity) {
        super(settler);
        this.vector = vector;
        this.entity = entity;
    }

    public Vector getCollisionVector() {
        return vector;
    }

    public Entity getPushedBy() {
        return entity;
    }
}
