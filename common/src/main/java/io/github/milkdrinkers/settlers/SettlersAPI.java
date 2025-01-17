package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.settlers.settler.Settler;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class SettlersAPI extends RegistryHolder {


    public static boolean isNPC(Entity e) {
        return e.hasMetadata("NPC");
    }

    public static boolean isSettler(Entity e) { // TODO
        return isNPC(e) && true; // TODO Perhaps mark settler entities as SETTLER
    }

    public static boolean isSettler(NPC e) { // TODO
        return true;
    }

    public static @Nullable Settler getSettler(NPC npc) { // TODO
        if (!isSettler(npc))
            return null;

        return new Settler(npc);
    }
}
