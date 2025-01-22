package io.github.milkdrinkers.settlers.api.enums;

public enum SettlerType {
    COMPANION("companions"),
    GUARD("guards"),
    NATION("nationfolk"),
    TOWN("tonwfolk");

    private final String name;

    SettlerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
