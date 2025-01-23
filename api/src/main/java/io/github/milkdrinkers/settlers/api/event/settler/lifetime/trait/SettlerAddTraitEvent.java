package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.trait.Trait;

public class SettlerAddTraitEvent extends SettlerTraitEvent {
    public SettlerAddTraitEvent(Settler settler, Trait trait) {
        super(settler, trait);
    }
}
