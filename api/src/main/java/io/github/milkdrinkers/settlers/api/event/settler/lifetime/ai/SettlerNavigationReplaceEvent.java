package io.github.milkdrinkers.settlers.api.event.settler.lifetime.ai;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.event.CancelReason;

public class SettlerNavigationReplaceEvent extends SettlerNavigationCancelEvent {
    public SettlerNavigationReplaceEvent(AbstractSettler settler, Navigator navigator) {
        super(settler, navigator, CancelReason.REPLACE);
    }
}
