package io.github.milkdrinkers.settlers.api.event.settler.lifetime;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;

public class SettlerCloneEvent extends AbstractSettlerEvent {
    private final Settler clone;

    public SettlerCloneEvent(Settler settler, Settler clone) {
        super(settler);
        this.clone = clone;
    }

    public Settler getClone() {
        return clone;
    }
}
