package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustByEntityEvent;

public class SettlerCombustByEntityEvent extends SettlerCombustEvent {
    private final EntityCombustByEntityEvent e;

    public SettlerCombustByEntityEvent(AbstractSettler settler, EntityCombustByEntityEvent e) {
        super(settler, e);
        this.e = e;
    }

    public Entity getCombuster() {
        return e.getCombuster();
    }

    public EntityCombustByEntityEvent getEvent() {
        return e;
    }

}
