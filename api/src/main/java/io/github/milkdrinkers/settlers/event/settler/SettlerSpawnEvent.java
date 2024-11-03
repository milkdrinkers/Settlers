package io.github.milkdrinkers.settlers.event.settler;

import io.github.milkdrinkers.settlers.enums.SpawnReason;
import io.github.milkdrinkers.settlers.settler.Settler;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SettlerSpawnEvent extends SettlerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    public Location location;

    public SpawnReason spawnReason;

    protected SettlerSpawnEvent(Settler settler, Location location, SpawnReason spawnReason) {
        super(settler);
        this.location = location;
        this.spawnReason = spawnReason;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public SpawnReason getSpawnReason() {
        return spawnReason;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
