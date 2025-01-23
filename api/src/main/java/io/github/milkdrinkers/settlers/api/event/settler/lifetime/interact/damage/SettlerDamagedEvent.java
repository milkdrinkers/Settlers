package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.event.entity.EntityDamageEvent;

public class SettlerDamagedEvent extends AbstractCancellableSettlerEvent { // TODO Mirror citizens event
    private boolean cancelled;
    private EntityDamageEvent e;

    public SettlerDamagedEvent(Settler settler, EntityDamageEvent e) {
        super(settler);
        this.e = e;
    }

    public EntityDamageEvent getEvent() {
        return e;
    }

}
