package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;

public class SettlerPistonPushEvent extends AbstractCancellableSettlerEvent {
    public SettlerPistonPushEvent(AbstractSettler settler) {
        super(settler);
    }
}
