package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.event.NPCRenameEvent;

public class SettlerRenameEvent extends AbstractCancellableSettlerEvent {
    private final NPCRenameEvent event;

    public SettlerRenameEvent(AbstractSettler settler, NPCRenameEvent event) {
        super(settler);
        this.event = event;
    }

    public String getNewName() {
        return event.getNewName();
    }

    public String getOldName() {
        return event.getOldName();
    }

    public void setNewName(String newName) {
        event.setNewName(newName);
    }
}
