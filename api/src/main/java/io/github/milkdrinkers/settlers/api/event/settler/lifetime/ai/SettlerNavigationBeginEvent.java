package io.github.milkdrinkers.settlers.api.event.settler.lifetime.ai;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.ai.Navigator;

public class SettlerNavigationBeginEvent extends SettlerNavigationEvent {
    public SettlerNavigationBeginEvent(AbstractSettler settler, Navigator navigator) {
        super(settler, navigator);
    }
}
