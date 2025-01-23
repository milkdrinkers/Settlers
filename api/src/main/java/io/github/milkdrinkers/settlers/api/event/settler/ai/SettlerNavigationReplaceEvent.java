package io.github.milkdrinkers.settlers.api.event.settler.ai;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.event.CancelReason;

public class SettlerNavigationReplaceEvent extends SettlerNavigationCancelEvent {
    public SettlerNavigationReplaceEvent(Settler settler, Navigator navigator) {
        super(settler, navigator, CancelReason.REPLACE);
    }
}
