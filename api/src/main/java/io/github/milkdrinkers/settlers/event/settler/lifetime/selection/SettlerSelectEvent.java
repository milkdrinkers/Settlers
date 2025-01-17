package io.github.milkdrinkers.settlers.event.settler.lifetime.selection;

import io.github.milkdrinkers.settlers.enums.DespawnReason;
import io.github.milkdrinkers.settlers.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.event.Cancellable;

public class SettlerSelectEvent extends AbstractSettlerEvent implements Cancellable { // TODO Mirror citizens event
    public DespawnReason spawnReason;
    private boolean cancelled;

    protected SettlerSelectEvent(Settler settler, DespawnReason spawnReason) {
        super(settler);
        this.spawnReason = spawnReason;
    }

    public DespawnReason getDespawnReason() {
        return spawnReason;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
