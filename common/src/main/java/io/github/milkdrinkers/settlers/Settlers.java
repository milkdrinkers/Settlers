package io.github.milkdrinkers.settlers;

import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.EntityType;

public class Settlers implements Reloadable {
    /**
     * On plugin load.
     */
    @Override
    public void onLoad() {
        RegistryHolder.getCompanionRegistry().createNPC(EntityType.PLAYER, "Herbert");
        for (var d : RegistryHolder.getRegistries()) {
        }
    }

    /**
     * On plugin enable.
     */
    @Override
    public void onEnable() {

    }

    /**
     * On plugin disable.
     */
    @Override
    public void onDisable() {

    }


}
