package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.event.settler.speech.SettlerSpeechEvent;
import net.citizensnpcs.api.ai.speech.event.NPCSpeechEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizensSpeechListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onMove(NPCSpeechEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerSpeechEvent(SettlersAPI.getSettler(e.getNPC()), e.getContext()).callEvent();
        e.setCancelled(cancelled);
    }
}
