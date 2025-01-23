package io.github.milkdrinkers.settlers.api.event.settler.ai;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.event.CancelReason;

public class SettlerNavigationCancelEvent extends SettlerNavigationCompleteEvent {
    private final CancelReason reason;

    public SettlerNavigationCancelEvent(Settler settler, Navigator navigator, CancelReason reason) {
        super(settler, navigator);
        this.reason = reason;
    }

    public CancelReason getCancelReason() {
        return reason;
    }
}
