package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.enums.CreateReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;

public class SettlerCreateEvent extends AbstractSettlerEvent {
    private final CreateReason reason;

    public SettlerCreateEvent(Settler settler, CreateReason reason) {
        super(settler);
        this.reason = reason;
    }

    public CreateReason getReason() {
        return reason;
    }
}
