package io.github.milkdrinkers.settlers.api.event.settler.ai;

import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.Navigator;

public class SettlerNavigationBeginEvent extends SettlerNavigationEvent {
    public SettlerNavigationBeginEvent(Settler settler, Navigator navigator) {
        super(settler, navigator);
    }
}
