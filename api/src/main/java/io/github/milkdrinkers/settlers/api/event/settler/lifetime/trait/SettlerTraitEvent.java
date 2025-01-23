package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.trait.Trait;

public class SettlerTraitEvent extends AbstractSettlerEvent {
    private final Trait trait;

    protected SettlerTraitEvent(Settler settler, Trait trait) {
        super(settler);
        this.trait = trait;
    }

    public Trait getTrait() {
        return trait;
    }
}
