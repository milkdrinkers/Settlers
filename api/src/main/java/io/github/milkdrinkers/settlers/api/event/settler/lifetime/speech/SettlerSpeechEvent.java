package io.github.milkdrinkers.settlers.api.event.settler.lifetime.speech;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.ai.speech.SpeechContext;

public class SettlerSpeechEvent extends AbstractCancellableSettlerEvent {
    private final SpeechContext context;

    public SettlerSpeechEvent(Settler settler, SpeechContext context) {
        super(settler);
        this.context = context;
    }

    public SpeechContext getContext() {
        return context;
    }
}
