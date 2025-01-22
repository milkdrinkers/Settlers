package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.SettlersPlugin;
import io.github.milkdrinkers.settlers.api.exception.SettlerSpawnException;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;

import static io.github.milkdrinkers.settlers.SettlersAPI.META_SETTLER;

public abstract class Settler {
    private final NPC npc;

    protected Settler(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }

    /*
    * TODO Rename spawn & despawn to load & unload perhaps?
    *
    * TODO Explain settler lifecycle
    * */

    /**
     * Returns {@link NPC#isSpawned()}.
     * @return boolean
     */
    public boolean isSpawned() {
        return npc.isSpawned();
    }

    /**
     * Executes {@link NPC#spawn(Location)} using the stored location of the settler.
     * @throws SettlerSpawnException if the stored location is null
     * @return boolean
     */
    public boolean spawn() throws SettlerSpawnException {
        final Location location = npc.getStoredLocation();
        if (location == null)
            throw new SettlerSpawnException("The settler does not have a stored location.");

        return spawn(location);
    }

    /**
     * Executes {@link NPC#spawn(Location)}.
     * @return boolean
     */
    public boolean spawn(Location location) {
        return spawn(location, SpawnReason.PLUGIN);
    }

    /**
     * Executes {@link NPC#spawn(Location, SpawnReason)}.
     * @return boolean
     */
    public boolean spawn(Location location, SpawnReason spawnReason) {
        if (isSpawned())
            return false;

        final boolean spawned = npc.spawn(location, spawnReason);
        npc.getEntity().setMetadata(META_SETTLER, new FixedMetadataValue(SettlersPlugin.getInstance(), META_SETTLER));

        return spawned;
    }

    /**
     * Executes {@link NPC#despawn()}.
     * @return boolean
     */
    public boolean despawn() {
        if (!isSpawned())
            return false;

        return despawn(DespawnReason.PLUGIN);
    }

    /**
     * Executes {@link NPC#despawn(DespawnReason)}.
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
}
