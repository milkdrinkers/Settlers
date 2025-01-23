package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityCombustByBlockEvent;

public class SettlerCombustByBlockEvent extends SettlerCombustEvent { // TODO Mirror citizens event
    private final EntityCombustByBlockEvent e;

    public SettlerCombustByBlockEvent(Settler settler, EntityCombustByBlockEvent e) {
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
