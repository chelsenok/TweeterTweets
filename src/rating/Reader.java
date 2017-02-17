package rating;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

class Reader {

    LinkedHashMap<String, Double> readSentiments(String fileName) {

        LinkedHashMap<String, Double> sentiments = new LinkedHashMap<>();

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {

            while (scanner.hasNext()) {
                sentiments.putAll(parseString(scanner.nextLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sentiments;
    }

    private LinkedHashMap<String, Double> parseString(String text) {
        String splits[] = text.split(",");
        LinkedHashMap<String, Double> hash = new LinkedHashMap<>();
        hash.put(splits[0], Double.valueOf(splits[1]));
        return hash;
    }
}
