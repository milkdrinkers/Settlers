package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustByEntityEvent;

public class SettlerCombustByEntityEvent extends SettlerCombustEvent { // TODO Mirror citizens event
    private final EntityCombustByEntityEvent e;

    public SettlerCombustByEntityEvent(Settler settler, EntityCombustByEntityEvent e) {
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
