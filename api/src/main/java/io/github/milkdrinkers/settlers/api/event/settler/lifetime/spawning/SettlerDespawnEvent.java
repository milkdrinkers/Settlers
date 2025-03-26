package io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.event.DespawnReason;

public class SettlerDespawnEvent extends AbstractCancellableSettlerEvent {
    private final DespawnReason reason;

    public SettlerDespawnEvent(AbstractSettler settler, DespawnReason reason) {
        super(settler);
        this.reason = reason;
    }

    public DespawnReason getReason() {
        return reason;
    }
}
