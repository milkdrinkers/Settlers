package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SettlerDamagedByEntityEvent extends SettlerDamagedEvent {
    private final EntityDamageByEntityEvent e;

    public SettlerDamagedByEntityEvent(AbstractSettler settler, EntityDamageByEntityEvent e) {
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
