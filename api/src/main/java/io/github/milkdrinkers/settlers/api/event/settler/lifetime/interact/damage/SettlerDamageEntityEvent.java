package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SettlerDamageEntityEvent extends AbstractCancellableSettlerEvent {
    private final EntityDamageByEntityEvent e;

    public SettlerDamageEntityEvent(AbstractSettler settler, EntityDamageByEntityEvent e) {
        super(settler);
        this.e = e;
    }

    public Entity getDamagee() {
        return e.getEntity();
    }

    public EntityDamageByEntityEvent getEvent() {
        return e;
    }
}
