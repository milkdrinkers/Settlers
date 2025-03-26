package io.github.milkdrinkers.settlers.api.event.settler.lifetime.ai;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.ai.Navigator;

public class SettlerNavigationCompleteEvent extends SettlerNavigationEvent {
    public SettlerNavigationCompleteEvent(AbstractSettler settler, Navigator navigator) {
        super(settler, navigator);
    }
}
