package io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;

public class SettlerPistonPushEvent extends AbstractCancellableSettlerEvent {
    public SettlerPistonPushEvent(Settler settler) {
        super(settler);
    }
}
