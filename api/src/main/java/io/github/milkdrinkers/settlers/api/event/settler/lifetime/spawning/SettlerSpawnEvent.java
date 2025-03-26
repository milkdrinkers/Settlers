package io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.event.SpawnReason;
import org.bukkit.Location;

public class SettlerSpawnEvent extends AbstractCancellableSettlerEvent {
    public Location location;
    public SpawnReason spawnReason;

    public SettlerSpawnEvent(AbstractSettler settler, Location location, SpawnReason spawnReason) {
        super(settler);
        this.location = location;
        this.spawnReason = spawnReason;
    }

    public Location getLocation() {
        return location;
    }

    public SpawnReason getSpawnReason() {
        return spawnReason;
    }
}
