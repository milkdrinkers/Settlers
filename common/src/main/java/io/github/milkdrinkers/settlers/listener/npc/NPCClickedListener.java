package io.github.milkdrinkers.settlers.listener.npc;

import io.github.milkdrinkers.settlers.api.enums.ClickType;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerClickedEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import io.github.milkdrinkers.settlers.api.settler.SettlerLookup;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;

public class NPCClickedListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCLeftClick(NPCLeftClickEvent e) {
        @Nullable Settler settler = SettlerLookup.lookup(e.getNPC());
        if (settler == null)
            return;

        final ClickType clickType;

        if (e.getClicker().isSneaking())
            clickType = ClickType.SHIFT_LEFT;
        else
            clickType = ClickType.LEFT;

        SettlerClickedEvent firedEvent = new SettlerClickedEvent(settler, clickType, e.getClicker());
        final boolean isCancelled = firedEvent.callEvent();
        e.setCancelled(isCancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCRightClick(NPCRightClickEvent e) {
        @Nullable Settler settler = SettlerLookup.lookup(e.getNPC());
        if (settler == null)
            return;

        final ClickType clickType;

        if (e.getClicker().isSneaking())
            clickType = ClickType.SHIFT_RIGHT;
        else
            clickType = ClickType.RIGHT;

        SettlerClickedEvent firedEvent = new SettlerClickedEvent(settler, clickType, e.getClicker());
        final boolean isCancelled = firedEvent.callEvent();
        e.setCancelled(isCancelled);
    }
}
