package io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.util.ChunkCoord;
import org.bukkit.Location;

public class SettlerNeedsRespawnEvent extends AbstractSettlerEvent {
    private final ChunkCoord spawnLocation;

    public SettlerNeedsRespawnEvent(AbstractSettler settler, ChunkCoord spawnLocation) {
        super(settler);
        this.spawnLocation = spawnLocation;
    }

    public ChunkCoord getSpawnLocation() {
        return spawnLocation;
    }
}
