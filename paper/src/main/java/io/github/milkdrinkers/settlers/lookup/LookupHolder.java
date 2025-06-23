package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.ILifecycle;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.registry.IRegistryHolder;
import io.github.milkdrinkers.settlers.utility.Utils;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;

/**
 * A holder for the lookup tables used by Settlers. Allows easily looking up entities and NPC's by their associated settler object or vice-versa.
 *
 * @see ILookupHolder
 * @see ILookupTable
 */
public class LookupHolder implements ILookupHolder, Listener, ILifecycle {
    private final ISettlersPlugin plugin;

    protected LookupHolder(ISettlersPlugin plugin) {
        this.plugin = plugin;
        this.npcILookupTable = new NPCLookupTable(plugin);
        this.entityILookupTable = new EntityLookupTable(plugin);
    }

    private final ILookupTable<AbstractSettler, NPC> npcILookupTable;
    private final ILookupTable<AbstractSettler, Entity> entityILookupTable;

    @Override
    public ILookupTable<AbstractSettler, NPC> getNpcLookupTable() {
        return npcILookupTable;
    }

    @Override
    public ILookupTable<AbstractSettler, Entity> getEntityLookupTable() {
        return entityILookupTable;
    }

    @ApiStatus.Internal
    private void clearTables() {
        npcILookupTable.clear();
        entityILookupTable.clear();
    }

    @Override
    public void onInit(ISettlersPlugin plugin) {
        final IRegistryHolder registries = plugin.getRegistryHandler().getRegistry();

        // Add NPC's & Entities to lookup tables
        for (NPC npc : registries.getRegistryCompanion()) {
            registerSettler(npc);
        }
        for (NPC npc : registries.getRegistryGuard()) {
            registerSettler(npc);
        }
        for (NPC npc : registries.getRegistryNation()) {
            registerSettler(npc);
        }
        for (NPC npc : registries.getRegistryTown()) {
            registerSettler(npc);
        }
    }

    /**
     * Registers a settler for the given NPC. This will create a new settler object and add it to the lookup tables.
     *
     * @param npc the NPC to register
     */
    @Override
    @ApiStatus.Internal
    public void registerSettler(NPC npc) {
        final AbstractSettler settler = Utils.createSettler(npc);
        if (settler == null) {
            plugin.getSLF4JLogger().warn("Failed to register settler for NPC: {}", npc.getName());
            return;
        }

        if (npc.isSpawned()) {
            final Entity entity = npc.getEntity();

            Utils.applySettlerEntityMetadata(npc, entity);
        }

        addToLookupTables(settler);
    }

    @Override
    public void onUnload(ISettlersPlugin plugin) {
        clearTables(); // Clear tables on api unload
    }
}
