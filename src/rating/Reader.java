package rating;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Scanner;

class Reader {

    public LinkedHashMap<String, Float> readSentiments(String fileName) {

        LinkedHashMap<String, Float> sentiments = new LinkedHashMap<>();

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {

            while (scanner.hasNext()) {
                sentiments.putAll(parseString(scanner.nextLine()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sentiments;
    }

    private LinkedHashMap<String, Float> parseString(String text) {
        String splits[] = text.split(",");
        LinkedHashMap<String, Float> hash = new LinkedHashMap<>();
        hash.put(splits[0], Float.valueOf(splits[1]));
        return hash;
    }
}
