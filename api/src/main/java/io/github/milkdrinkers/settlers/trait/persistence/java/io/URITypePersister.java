package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.net.URI;

public class URITypePersister implements Persister<URI> {
    @Override
    public URI create(DataKey dataKey) {
        return URI.create(dataKey.getString("", ""));
    }

    @Override
    public void save(URI uri, DataKey dataKey) {
        dataKey.setString("", uri.toString());
    }
}