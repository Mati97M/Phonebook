package dev.mmieckowski.utilities;

import dev.mmieckowski.PersonPhone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utilities {
    private static final List<PersonPhone> unsortedList = new ArrayList<>(
            List.of(
                    new PersonPhone("Gabrila Araminta", 59339418),
                    new PersonPhone("Jan Atiana", 60478194),
                    new PersonPhone("Amalia Gaelan", 75554798),
                    new PersonPhone("Heida Pavlish", 20560628),
                    new PersonPhone("Ula Dieball", 19323587)

            )
    );
    private static final List<PersonPhone> sortedList = new ArrayList<>(unsortedList);
    private static final List<String> targets;

    static {
        sortedList.sort(PersonPhone::compareTo);
        targets = unsortedList.stream()
                .map(PersonPhone::name)
                .toList();
    }

    public static List<PersonPhone> getUnsortedList() {
        return new ArrayList<>(unsortedList);
    }

    public static List<PersonPhone> getSortedList() {
        return new ArrayList<>(sortedList);
    }

    public static List<String> getTargets() {
        return new ArrayList<>(targets);
    }
}
