package rating;

import java.util.*;

public class AssignRating {

    // TODO: 17.02.2017 make list of on/at/in/the/a to concatenate words into collocation
    // TODO: 19.02.2017 count retweets and likes
    // TODO: 20.02.2017 delete founded words from mTweetWords

    private final String DICTIONARY_NAME = "res/sentiments.csv";

    private HashMap<Integer, HashMap<String, Float>> mQuantitySentimentsMap;
    private float mValue;
    private int mCount;
    private ArrayList<String> mTweetWords;
    private static AssignRating mInstance;

    private AssignRating() {
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

    public static AssignRating getInstance() {
        if (mInstance == null) {
            mInstance = new AssignRating();
        }
        return mInstance;
    }

    public float assignTweetRating(String text) {
//        text = new TextCleaner.clean(text.toLowerCase());
        mTweetWords = new ArrayList<>(Arrays.asList(text.split(" ")));
        mValue = 0;
        mCount = 0;

        calculate(mTweetWords.size());
        if (mCount != 0) {
            return mValue / mCount;
        }
        return 0;
    }

    private void calculate(int quantity) {
        for (int i = 0; i + quantity <= mTweetWords.size(); i++) {
            String str = "";
            for (String s :
                    mTweetWords.subList(i, i + quantity)) {
                str += s + " ";
            }
            System.out.println(str);
            mValue += getWordAssign(quantity, str.substring(0, str.length() - 1));
        }
        if (quantity != 1) {
            calculate(quantity - 1);
        }
    }

    private float getWordAssign(int quantity, String str) {
        try {
            float value = mQuantitySentimentsMap.get(quantity).get(str);
            mCount++;
            return value;
        } catch (NullPointerException exc) {
            return 0;
        }
    }
}
