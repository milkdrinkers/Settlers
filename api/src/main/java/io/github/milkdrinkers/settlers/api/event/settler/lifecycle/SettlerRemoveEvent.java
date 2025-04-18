package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.enums.RemoveReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;

public class SettlerRemoveEvent extends AbstractSettlerEvent {
    private final RemoveReason reason;

    public SettlerRemoveEvent(AbstractSettler settler, RemoveReason reason) {
        super(settler);
        this.reason = reason;
    }

    public RemoveReason getReason() {
        return reason;
    }
}
