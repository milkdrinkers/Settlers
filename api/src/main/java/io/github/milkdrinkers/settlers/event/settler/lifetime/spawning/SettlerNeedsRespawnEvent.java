package io.github.milkdrinkers.settlers.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.enums.SpawnReason;
import io.github.milkdrinkers.settlers.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;

/**
 * Run when...
 */
public class SettlerNeedsRespawnEvent extends AbstractSettlerEvent implements Cancellable {
    public Location location;
    public SpawnReason spawnReason;
    private boolean cancelled;

    protected SettlerNeedsRespawnEvent(Settler settler, Location location, SpawnReason spawnReason) {
        super(settler);
        this.location = location;
        this.spawnReason = spawnReason;
    }

    public SpawnReason getSpawnReason() {
        return spawnReason;
    }

    public Location getLocation() {
        return location;
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
