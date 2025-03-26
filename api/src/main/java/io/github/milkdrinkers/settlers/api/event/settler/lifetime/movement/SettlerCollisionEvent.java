package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;

public class SettlerCollisionEvent extends AbstractSettlerEvent {
    private final Entity collidedWith;

    public SettlerCollisionEvent(AbstractSettler settler, Entity collidedWith) {
        super(settler);
        this.collidedWith = collidedWith;
    }

    public Entity getCollidedWith() {
        return collidedWith;
    }
}
