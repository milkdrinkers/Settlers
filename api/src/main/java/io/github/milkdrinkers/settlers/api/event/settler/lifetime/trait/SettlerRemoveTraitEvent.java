package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.trait.Trait;

public class SettlerRemoveTraitEvent extends SettlerTraitEvent {
    public SettlerRemoveTraitEvent(AbstractSettler settler, Trait trait) {
        super(settler, trait);
    }
}
