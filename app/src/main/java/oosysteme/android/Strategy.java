package oosysteme.android;

import android.graphics.drawable.Drawable;

public class Strategy {

    private final String name;

    private final String description;

    private final StrategyType type;

    private final Drawable icon;

    private boolean active;

    public Strategy(String name, String description, StrategyType type, Drawable icon) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.icon = icon;
        this.active = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StrategyType getType() {
        return type;
    }

    public Drawable getIcon() {
        return icon;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
