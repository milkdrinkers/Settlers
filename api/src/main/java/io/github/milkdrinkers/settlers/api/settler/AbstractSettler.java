package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import io.github.milkdrinkers.settlers.api.exception.SettlerSpawnException;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractSettler {
    private final NPC npc;

    protected AbstractSettler(NPC npc) {
        this.npc = npc;
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
     * Check if the settler is a companion.
     *
     * @return true if the settler is a companion, false otherwise
     */
    public boolean isCompanion() {
        return SettlersAPI.isCompanion(npc);
    }

    /**
     * Check if the settler is a guard.
     *
     * @return true if the settler is a guard, false otherwise
     */
    public boolean isGuard() {
        return SettlersAPI.isGuard(npc);
    }

    /**
     * Check if the settler is a nation folk.
     *
     * @return true if the settler is a nation folk, false otherwise
     */
    public boolean isNationFolk() {
        return SettlersAPI.isNationFolk(npc);
    }

    /**
     * Check if the settler is a town folk.
     *
     * @return true if the settler is a town folk, false otherwise
     */
    public boolean isTownFolk() {
        return SettlersAPI.isTownFolk(npc);
    }

    /**
     * Get what type of settler this is
     *
     * @return a settler type or null if unknown
     */
    public @Nullable SettlerType getType() {
        return SettlersAPI.getSettlerType(getNpc());
    }

    /**
     * Returns the {@link SettlerBuilder} used for creating new Settlers.
     * @return {@link SettlerBuilder}
     */
    public static SettlerBuilder builder() {
        return new SettlerBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractSettler settler)) return false;
        return Objects.equals(hashCode(), settler.hashCode());
    }

    @Override
    public int hashCode() {
        return getNpc().getUniqueId().hashCode();
    }
}
