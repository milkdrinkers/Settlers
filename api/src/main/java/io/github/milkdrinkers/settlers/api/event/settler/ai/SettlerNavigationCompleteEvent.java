package io.github.milkdrinkers.settlers.api.event.settler.ai;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.Navigator;

public class SettlerNavigationCompleteEvent extends SettlerNavigationEvent {
    public SettlerNavigationCompleteEvent(Settler settler, Navigator navigator) {
        super(settler, navigator);
    }
}
