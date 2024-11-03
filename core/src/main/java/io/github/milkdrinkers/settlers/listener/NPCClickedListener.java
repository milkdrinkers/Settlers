package io.github.milkdrinkers.settlers.listener;

import io.github.milkdrinkers.settlers.enums.ClickType;
import io.github.milkdrinkers.settlers.event.settler.SettlerClickedEvent;
import io.github.milkdrinkers.settlers.settler.SettlerLookup;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCClickedListener implements Listener {

    @EventHandler
    public void onNPCLeftClick(NPCLeftClickEvent e) {
        ClickType clickType;

        if (e.getClicker().isSneaking())
            clickType = ClickType.SHIFT_LEFT;
        else
            clickType = ClickType.LEFT;

        SettlerClickedEvent firedEvent = new SettlerClickedEvent(clickType, e.getClicker(), SettlerLookup.lookupSettler(e.getNPC()));
        Bukkit.getPluginManager().callEvent(firedEvent);
    }

    @EventHandler
    public void onNPCRightClick(NPCRightClickEvent e) {
        ClickType clickType;

        if (e.getClicker().isSneaking())
            clickType = ClickType.SHIFT_RIGHT;
        else
            clickType = ClickType.RIGHT;

        SettlerClickedEvent firedEvent = new SettlerClickedEvent(clickType, e.getClicker(), SettlerLookup.lookupSettler(e.getNPC()));
        Bukkit.getPluginManager().callEvent(firedEvent);
    }

}
