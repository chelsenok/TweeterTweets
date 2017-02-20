package search.Local;

public enum Query {
    MyLife {
        @Override
        String path() {
            return "res/my_life.txt";
        }
    },
    Obama {
        @Override
        String path() {
            return "res/obama.txt";
        }
    },
    AllTweets {
        @Override
        String path() {
            return "res/all_tweets.txt";
        }
    },
    MyJob {
        @Override
        String path() {
            return "res/my_job.txt";
        }
    },
    Sandwich {
        @Override
        String path() {
            return "res/sandwich.txt";
        }
    },
    Texas {
        @Override
        String path() {
            return "res/texas.txt";
        }
    };

    abstract String path();
}
