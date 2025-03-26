package io.github.milkdrinkers.settlers.listener.citizensapi;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.speech.SettlerSpeechEvent;
import net.citizensnpcs.api.ai.speech.event.NPCSpeechEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Handles calling settlers events resulting from Citizens NPC speech related events
 */
public class CitizensSpeechListener implements Listener {
    private final ISettlersPlugin plugin;

    public CitizensSpeechListener(ISettlersPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onSpeech(NPCSpeechEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerSpeechEvent(SettlersAPI.getSettler(e.getNPC()), e.getContext()).callEvent();
        e.setCancelled(cancelled);
    }
}
