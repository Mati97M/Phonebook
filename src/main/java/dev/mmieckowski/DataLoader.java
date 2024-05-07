package dev.mmieckowski;

import lombok.NoArgsConstructor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class DataLoader {

    List<PersonPhone> getDataFromFile(String dataDir) {
        List<PersonPhone> data = new LinkedList<>();
        long phoneNum;
        String person;
        try (Scanner scanner = new Scanner(new File(dataDir), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                try (Scanner line = new Scanner(scanner.nextLine())) {
                    phoneNum = line.nextLong();
                    line.skip(" ");
                    person = line.nextLine();
                }
                data.add(new PersonPhone(person, phoneNum));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + dataDir);
        } catch (IOException e) {
            System.out.println("Some problems occurred while loading data");
        }
        return data;
    }

    List<String> getTargetsFromFile(String targetsDir) {
        List<String> targets = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File(targetsDir), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                targets.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + targetsDir);
        } catch (IOException e) {
            System.out.println("Some problems occurred while loading data");
        }
        return targets;
    }
}
