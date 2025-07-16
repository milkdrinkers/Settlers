package io.github.milkdrinkers.settlers.listener.citizensapi;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.ai.*;
import net.citizensnpcs.api.ai.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles calling settlers events resulting from Citizens NPC navigation related events
 */
public class CitizensNavigationListener implements Listener {
    private final ISettlersPlugin plugin;

    public CitizensNavigationListener(ISettlersPlugin plugin) {
        this.plugin = plugin;
    }

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
