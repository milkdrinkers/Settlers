package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.event.entity.EntityDamageEvent;

public class SettlerDamagedEvent extends AbstractCancellableSettlerEvent {
    private final EntityDamageEvent e;

    public SettlerDamagedEvent(AbstractSettler settler, EntityDamageEvent e) {
        super(settler);
        this.e = e;
    }

    public EntityDamageEvent getEvent() {
        return e;
    }

}
