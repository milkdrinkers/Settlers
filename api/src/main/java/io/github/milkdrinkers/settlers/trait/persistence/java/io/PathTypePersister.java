package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTypePersister implements Persister<Path> {
    @Override
    public Path create(DataKey dataKey) {
        return Paths.get(dataKey.getString("", ""));
    }

    @Override
    public void save(Path path, DataKey dataKey) {
        dataKey.setString("", path.toString());
    }
}