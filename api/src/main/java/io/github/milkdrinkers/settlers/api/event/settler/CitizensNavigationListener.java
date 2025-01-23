package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.event.settler.ai.*;
import net.citizensnpcs.api.ai.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizensNavigationListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onNavBegin(NavigationBeginEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNavigationBeginEvent(SettlersAPI.getSettler(e.getNPC()), e.getNavigator()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNavCancel(NavigationCancelEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNavigationCancelEvent(SettlersAPI.getSettler(e.getNPC()), e.getNavigator(), e.getCancelReason()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNavComplete(NavigationCompleteEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNavigationCompleteEvent(SettlersAPI.getSettler(e.getNPC()), e.getNavigator()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNavReplace(NavigationReplaceEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNavigationReplaceEvent(SettlersAPI.getSettler(e.getNPC()), e.getNavigator()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNavStuck(NavigationStuckEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNavigationStuckEvent(SettlersAPI.getSettler(e.getNPC()), e.getNavigator(), e).callEvent();
    }
}
