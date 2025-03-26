package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityCombustByBlockEvent;

public class SettlerCombustByBlockEvent extends SettlerCombustEvent {
    private final EntityCombustByBlockEvent e;

    public SettlerCombustByBlockEvent(AbstractSettler settler, EntityCombustByBlockEvent e) {
        super(settler, e);
        this.e = e;
    }

    public Block getCombuster() {
        return e.getCombuster();
    }

    public EntityCombustByBlockEvent getEvent() {
        return e;
    }
}
