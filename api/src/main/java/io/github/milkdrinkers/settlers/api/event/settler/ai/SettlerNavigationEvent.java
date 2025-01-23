package io.github.milkdrinkers.settlers.api.event.settler.ai;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.Navigator;

public abstract class SettlerNavigationEvent extends AbstractSettlerEvent {
    private final Navigator navigator;

    protected SettlerNavigationEvent(Settler settler, Navigator navigator) {
        super(settler);
        this.navigator = navigator;
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
