package de.ossysteme;

public interface Factory<TValue, TType> {
    TValue create(TType type);
}
