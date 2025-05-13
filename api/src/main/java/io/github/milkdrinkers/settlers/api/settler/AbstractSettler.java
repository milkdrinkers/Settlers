package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.CreateReason;
import io.github.milkdrinkers.settlers.api.event.settler.lifecycle.SettlerCreateEvent;
import io.github.milkdrinkers.settlers.api.exception.SettlerSpawnException;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSettler {
    private final NPC npc;

    protected AbstractSettler(NPC npc) {
        this.npc = npc;
        new SettlerCreateEvent(this, CreateReason.UNKNOWN).callEvent();
        SettlersAPI.getImplementation().getLookupHandler().getHolder().getNpcLookupTable().add(this, npc);
    }

    public NPC getNpc() {
        return npc;
    }

    /**
     * Returns the entity of the settler if the settler is spawned.
     * @return {@link Entity} or null
     * @implNote Use {@link #isSpawned()} to check if the settler is spawned.
     * @see #isSpawned()
     */
    public @Nullable Entity getEntity() {
        return npc.getEntity();
    }

    /**
     * Returns {@link NPC#isSpawned()}.
     *
     * @return boolean
     */
    public boolean isSpawned() {
        return npc.isSpawned();
    }

    /**
     * Executes {@link NPC#spawn(Location)} using the stored location of the settler.
     *
     * @return boolean
     * @throws SettlerSpawnException if the stored location is null
     */
    public boolean spawn() throws SettlerSpawnException {
        final Location location = npc.getStoredLocation();
        if (location == null)
            throw new SettlerSpawnException("The settler does not have a stored location.");

        return spawn(location);
    }

    /**
     * Executes {@link NPC#spawn(Location)}.
     *
     * @return boolean
     */
    public boolean spawn(Location location) {
        return spawn(location, SpawnReason.PLUGIN);
    }

    /**
     * Executes {@link NPC#spawn(Location, SpawnReason)}.
     *
     * @return boolean
     */
    public boolean spawn(Location location, SpawnReason spawnReason) {
        if (isSpawned())
            return false;

        return npc.spawn(location, spawnReason);
    }

    /**
     * Executes {@link NPC#despawn()}.
     *
     * @return boolean
     */
    public boolean despawn() {
        if (!isSpawned())
            return false;

        return despawn(DespawnReason.PLUGIN);
    }

    /**
     * Executes {@link NPC#despawn(DespawnReason)}.
     *
     * @return boolean
     */
    public boolean despawn(DespawnReason despawnReason) {
        if (!isSpawned())
            return false;

        return npc.despawn(despawnReason);
    }

    /**
     * Deletes this settler from its related registry.
     */
    public void delete() {
        npc.destroy();
    }

    /**
     * Returns the {@link SettlerBuilder} used for creating new Settlers.
     * @return {@link SettlerBuilder}
     */
    public static SettlerBuilder builder() {
        return new SettlerBuilder();
    }
}
