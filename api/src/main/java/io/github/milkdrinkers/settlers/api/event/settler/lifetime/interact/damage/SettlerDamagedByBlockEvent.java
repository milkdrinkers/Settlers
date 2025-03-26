package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class SettlerDamagedByBlockEvent extends SettlerDamagedEvent {
    private final EntityDamageByBlockEvent e;

    public SettlerDamagedByBlockEvent(AbstractSettler settler, EntityDamageByBlockEvent e) {
        super(settler, e);
        this.e = e;
    }

    public Block getDamager() {
        return e.getDamager();
    }

    public EntityDamageByBlockEvent getEvent() {
        return e;
    }

}
