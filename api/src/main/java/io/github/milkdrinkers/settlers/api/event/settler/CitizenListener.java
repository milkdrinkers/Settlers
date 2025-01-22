package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.RemoveReason;
import io.github.milkdrinkers.settlers.api.event.settler.lifecycle.SettlerRemoveEvent;
import net.citizensnpcs.api.event.NPCRemoveByCommandSenderEvent;
import net.citizensnpcs.api.event.NPCRemoveEvent;
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
