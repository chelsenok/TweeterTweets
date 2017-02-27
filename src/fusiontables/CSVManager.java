package fusiontables;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class CSVManager {

    private static ArrayList<String> sKeys;
    private static final String LINE_SEPARATOR = "\r\n";
    private static final char SEPARATOR = ',';

    public static void update(String path, HashMap<String, String> mapKeyValue, String field, String defaultValue) {
        ArrayList<HashMap<String, String>> mapArrayList = readTable(path);

        for (HashMap<String, String> map :
                mapArrayList) {
            for (Map.Entry<String, String> entry :
                    map.entrySet()) {
                String s = mapKeyValue.get(entry.getValue());
                if (s != null) {
                    map.put(field, s);
                    break;
                } else {
                    map.put(field, defaultValue);
                }
            }
        }

        writeTable(path, mapArrayList);
    }

    private static ArrayList<HashMap<String, String>> readTable(String path) {
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();

        boolean getKeys = false;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (getKeys) {
                    ArrayList<String> values = splitBySign(line);
                    HashMap<String, String> mapKeyValue = new HashMap<>();
                    for (int i = 0; i < sKeys.size(); i++) {
                        try {
                            mapKeyValue.put(sKeys.get(i), values.get(i));
                        } catch (Exception e) {
                            mapKeyValue.put(sKeys.get(i), "");
                        }
                    }
                    mapArrayList.add(mapKeyValue);
                } else {
                    sKeys = splitBySign(line);
                    getKeys = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapArrayList;
    }

    private static void writeTable(String path, ArrayList<HashMap<String, String>> mapArrayList) {
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");

            for (String key :
                    sKeys) {
                writer.print(key + SEPARATOR);
            }
            for (HashMap<String, String> map :
                    mapArrayList) {
                writer.print(LINE_SEPARATOR);
                for (String key :
                        sKeys) {
                    writer.print(map.get(key) + SEPARATOR);
                }
            }

            writer.close();
        } catch (IOException ignored) {
        }
    }

    private static ArrayList<String> splitBySign(String line) {
        ArrayList<String> strings = new ArrayList<>();

        int prev = 0;
        boolean inQuotes = false;
        final int length = line.length();
        for (int i = 0; i < length; i++) {
            char current = line.charAt(i);
            if (!inQuotes) {
                if (current == SEPARATOR) {
                    strings.add(line.substring(prev, i));
                    prev = i + 1;
                } else if (i == length - 1) {
                    strings.add(line.substring(prev, i + 1));
                }
            }
            if (current == '\"') {
                inQuotes = !inQuotes;
            }
        }

        return strings;
    }

}
