package io.github.milkdrinkers.settlers.listener.citizensapi;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerDespawnEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerSpawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizenLookupTableListener implements Listener {
    private final ISettlersPlugin plugin;

    public CitizenLookupTableListener(ISettlersPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void addEntity(SettlerSpawnEvent e) {
        // Entity should always be valid by this point, or do nothing
        if (!e.getSettler().isSpawned())
            return;

        plugin.getLookupHandler().getHolder().getEntityLookupTable().add(
            e.getSettler(),
            e.getSettler().getNpc().getEntity()
        );
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void removeEntity(SettlerDespawnEvent e) {
        // Entity should always be valid by this point, or do nothing
        if (!e.getSettler().isSpawned())
            return;

        plugin.getLookupHandler().getHolder().getEntityLookupTable().removeByKey(e.getSettler());
    }
}
