package org.poo.entities;

import java.util.Objects;

public class CurrencyPair {
    private final String from;
    private final String to;

    public CurrencyPair(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrencyPair that = (CurrencyPair) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
