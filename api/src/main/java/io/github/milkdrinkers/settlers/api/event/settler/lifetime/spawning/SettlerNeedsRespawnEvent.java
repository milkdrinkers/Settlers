package io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.Location;

public class SettlerNeedsRespawnEvent extends AbstractSettlerEvent {
    private final Location spawnLocation;

    public SettlerNeedsRespawnEvent(Settler settler, Location spawnLocation) {
        super(settler);
        this.spawnLocation = spawnLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
}
