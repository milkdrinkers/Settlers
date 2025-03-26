package io.github.milkdrinkers.settlers.api.event.settler.lifetime.ai;

import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.StuckAction;
import net.citizensnpcs.api.ai.event.NavigationStuckEvent;

public class SettlerNavigationStuckEvent extends SettlerNavigationEvent {
    private final NavigationStuckEvent event;

    public SettlerNavigationStuckEvent(AbstractSettler settler, Navigator navigator, NavigationStuckEvent event) {
        super(settler, navigator);
        this.event = event;
    }

    public StuckAction getAction() {
        return event.getAction();
    }

    public void setAction(StuckAction action) {
        event.setAction(action);
    }
}
