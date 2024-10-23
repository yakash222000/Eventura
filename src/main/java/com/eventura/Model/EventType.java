package com.eventura.Model;

public enum EventType {
    CONCERT("Concert"),
    MOVIE("Movie"),
    SPORTS("Sports"),
    CONFERENCE("Conference"),
    WORKSHOP("Workshop"),
    FESTIVAL("Festival"),
    EXHIBITION("Exhibition"),
    PARTY("Party");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
