package io.github.milkdrinkers.settlers.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.enums.RemoveReason;
import io.github.milkdrinkers.settlers.event.settler.lifecycle.PlayerCreateSettlerEvent;
import io.github.milkdrinkers.settlers.event.settler.lifecycle.SettlerRemoveEvent;
import io.github.milkdrinkers.settlers.settler.Settler;
import net.citizensnpcs.api.event.NPCCreateEvent;
import net.citizensnpcs.api.event.NPCRemoveByCommandSenderEvent;
import net.citizensnpcs.api.event.NPCRemoveEvent;
import net.citizensnpcs.api.event.PlayerCreateNPCEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizenListener implements Listener {
    @EventHandler
    public void onNPCRemove(NPCRemoveEvent e) {
        Bukkit.getPluginManager().callEvent(new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.UNKNOWN));
    }

    @EventHandler
    public void onNPCRemoveByCommandSender(NPCRemoveByCommandSenderEvent e) {
        Bukkit.getPluginManager().callEvent(new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.COMMAND));
    }
}
