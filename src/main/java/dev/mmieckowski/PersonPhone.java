package dev.mmieckowski;

public record PersonPhone(String name, long phone) implements Comparable<PersonPhone> {
    @Override
    public int compareTo(PersonPhone other) {
        if (other == null) {
            throw new NullPointerException();
        }
        return name.compareTo(other.name);
    }
}