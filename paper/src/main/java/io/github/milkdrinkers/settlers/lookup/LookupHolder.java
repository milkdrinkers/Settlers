package io.github.milkdrinkers.settlers.lookup;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.ILifecycle;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import io.github.milkdrinkers.settlers.api.settler.Nationfolk;
import io.github.milkdrinkers.settlers.api.settler.SettlerBuilder;
import io.github.milkdrinkers.settlers.registry.IRegistryHolder;
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
    }

    private final ILookupTable<AbstractSettler, NPC> npcILookupTable = new LookupTable<>();
    private final ILookupTable<AbstractSettler, Entity> entityILookupTable = new LookupTable<>();

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
            final AbstractSettler settler = new SettlerBuilder()
                .setNpc(npc)
                .createCompanion();

            getNpcLookupTable().add(settler, npc);
            if (settler.isSpawned())
                getEntityLookupTable().add(settler, npc.getEntity());
        }
        for (NPC npc : registries.getRegistryGuard()) {
            final AbstractSettler settler = new SettlerBuilder()
                .setNpc(npc)
                .createGuard();

            getNpcLookupTable().add(settler, npc);
            if (settler.isSpawned())
                getEntityLookupTable().add(settler, npc.getEntity());
        }
        for (NPC npc : registries.getRegistryNation()) {
            final Nationfolk settler = new SettlerBuilder()
                .setNpc(npc)
                .createNationfolk();

            getNpcLookupTable().add(settler, npc);
            if (settler.isSpawned())
                getEntityLookupTable().add(settler, npc.getEntity());
        }
        for (NPC npc : registries.getRegistryTown()) {
            final AbstractSettler settler = new SettlerBuilder()
                .setNpc(npc)
                .createTownfolk();

            getNpcLookupTable().add(settler, npc);
            if (settler.isSpawned())
                getEntityLookupTable().add(settler, npc.getEntity());
        }
    }

    @Override
    public void onUnload(ISettlersPlugin plugin) {
        clearTables(); // Clear tables on api unload
    }
}
