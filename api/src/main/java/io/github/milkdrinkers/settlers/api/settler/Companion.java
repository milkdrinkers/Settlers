package io.github.milkdrinkers.settlers.api.settler;

import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;

public class Companion extends AbstractSettler {
    protected Companion(NPC npc) {
        super(npc);
    }

    @Override
    public boolean spawn(Location location, SpawnReason spawnReason) {
        return super.spawn(location, spawnReason);
    }
}
