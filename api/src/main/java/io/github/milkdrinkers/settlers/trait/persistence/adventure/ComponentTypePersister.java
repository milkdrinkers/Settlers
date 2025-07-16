package io.github.milkdrinkers.settlers.trait.persistence.adventure;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ComponentTypePersister implements Persister<Component> {
    @Override
    public Component create(DataKey dataKey) {
        final String json = dataKey.getString("", "{}");
        try {
            return GsonComponentSerializer.gson().deserialize(json);
        } catch (Exception e) {
            return Component.empty();
        }
    }

    @Override
    public void save(Component component, DataKey dataKey) {
        dataKey.setString("", GsonComponentSerializer.gson().serialize(component));
    }
}