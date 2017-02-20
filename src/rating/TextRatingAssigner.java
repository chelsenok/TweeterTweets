package rating;

import java.util.*;

public class TextRatingAssigner {

    // TODO: 17.02.2017 make list of on/at/in/the/a to concatenate words into collocation
    // TODO: 19.02.2017 count retweets and likes

    private final String DICTIONARY_NAME = "res/sentiments.csv";

    private HashMap<Integer, HashMap<String, Float>> mQuantitySentimentsMap;
    private float mValue;
    private int mCount;
    private ArrayList<String> mTweetWords;
    private static TextRatingAssigner mInstance;

    private TextRatingAssigner() {
        LinkedHashMap<String, Float> sentiments = new Reader().readSentiments(DICTIONARY_NAME);
        mQuantitySentimentsMap = new HashMap<>();
        for (Map.Entry<String, Float> entry :
                sentiments.entrySet()) {

            String string = entry.getKey();
            int wordsCount = string.split(" ").length;
            if (!mQuantitySentimentsMap.containsKey(wordsCount)) {
                mQuantitySentimentsMap.put(wordsCount, new HashMap<>());
            }
            mQuantitySentimentsMap.get(wordsCount).put(string.toLowerCase(), entry.getValue());
        }
    }

    public static TextRatingAssigner getInstance() {
        if (mInstance == null) {
            mInstance = new TextRatingAssigner();
        }
        return mInstance;
    }

    public float get(String text) {
        String[] words = TextCleaner.clean(text.toLowerCase());
        mTweetWords = new ArrayList<>(Arrays.asList(words));
        mValue = 0;
        mCount = 0;

        calculate(mTweetWords.size());
        if (mCount != 0) {
            return mValue / mCount;
        }
        return 0;
    }

    private void calculate(int quantity) {
        if (quantity == 0) {
            return;
        }
        boolean isChecked = false;
        for (int i = 0; i + quantity <= mTweetWords.size(); i++) {
            float value = getWordAssign(quantity, mTweetWords.subList(i, i + quantity));
            if (value != 0) {
                mValue += value;
                isChecked = true;
            }
        }
        if (isChecked && quantity != 1) {
            calculate(mTweetWords.size());
        } else {
            calculate(quantity - 1);
        }
    }

    private float getWordAssign(int quantity, List<String> subList) {
        String str = "";
        for (String s : subList
                ) {
            str += s + " ";
        }

        System.out.println(str);

        float value;
        try {
            value = mQuantitySentimentsMap.get(quantity).get(str.substring(0, str.length() - 1));
            System.out.println("*******   " + str + "   *******");
        } catch (NullPointerException exc) {
            return 0;
        }
        mCount++;
        mTweetWords.removeAll(subList);
        return value;
    }
}
