package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class SettlerKnockbackEvent extends AbstractCancellableSettlerEvent { // TODO Mirror citizens event
    private final Entity entity;
    private final double strength;
    private final Vector vector;

    public SettlerKnockbackEvent(Settler settler, double strength, Vector vector, Entity entity) {
        super(settler);
        this.entity = entity;
        this.strength = strength;
        this.vector = vector;
    }

    public Vector getKnockbackVector() {
        return vector;
    }

    public Entity getKnockingBackEntity() {
        return entity;
    }

    public double getStrength() {
        return strength;
    }

}
