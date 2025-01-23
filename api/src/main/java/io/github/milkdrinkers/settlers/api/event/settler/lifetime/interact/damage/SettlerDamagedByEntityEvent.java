package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SettlerDamagedByEntityEvent extends SettlerDamagedEvent { // TODO Mirror citizens event
    private final EntityDamageByEntityEvent e;

    public SettlerDamagedByEntityEvent(Settler settler, EntityDamageByEntityEvent e) {
        super(settler, e);
        this.e = e;
    }

    public EntityDamageByEntityEvent getEvent() {
        return e;
    }

    public Entity getDamager() {
        return e.getDamager();
    }
}
