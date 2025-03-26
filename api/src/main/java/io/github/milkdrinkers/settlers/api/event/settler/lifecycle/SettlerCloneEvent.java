package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;

public class SettlerCloneEvent extends AbstractSettlerEvent {
    private final AbstractSettler clone;

    public SettlerCloneEvent(AbstractSettler settler, AbstractSettler clone) {
        super(settler);
        this.clone = clone;
    }

    public AbstractSettler getClone() {
        return clone;
    }
}
