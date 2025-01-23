package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.event.entity.EntityCombustEvent;

public class SettlerCombustEvent extends AbstractCancellableSettlerEvent {
    private final EntityCombustEvent e;

    public SettlerCombustEvent(Settler settler, EntityCombustEvent e) {
        super(settler);
        this.e = e;
    }

    public EntityCombustEvent getEvent() {
        return e;
    }
}
