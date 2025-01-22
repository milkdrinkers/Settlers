package io.github.milkdrinkers.settlers.trait;

import io.github.milkdrinkers.settlers.Reloadable;
import io.github.milkdrinkers.settlers.SettlersPlugin;
import io.github.milkdrinkers.settlers.api.trait.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

import java.util.List;

/**
 * A handler for managing {@link TraitRegistry} access & life-cycle.
 */
public class TraitRegistryHandler implements Reloadable {
    private final TraitRegistry registry = new TraitRegistry();

    @Override
    public void onLoad(SettlersPlugin plugin) {
        final List<TraitInfo> defaultTraits = List.of(
            TraitInfo.create(SettlerTrait.class).withName("settler"),
            TraitInfo.create(CompanionTrait.class).withName("companion"),
            TraitInfo.create(GuardTrait.class).withName("guard"),
            TraitInfo.create(NationFolkTrait.class).withName("nationfolk"),
            TraitInfo.create(TownFolkTrait.class).withName("townfolk")
        );

        for (TraitInfo trait : defaultTraits)
            getRegistry().addTrait(trait);
    }

    @Override
    public void onEnable(SettlersPlugin plugin) {
        for (TraitInfo trait : getRegistry())
            CitizensAPI.getTraitFactory().registerTrait(trait);
    }

    @Override
    public void onDisable(SettlersPlugin plugin) {
        for (TraitInfo trait : getRegistry())
            CitizensAPI.getTraitFactory().deregisterTrait(trait);
        getRegistry().clearTraits();
    }

    public TraitRegistry getRegistry() {
        return registry;
    }
}
