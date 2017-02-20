package tweet;

import org.jetbrains.annotations.Nullable;

public interface TweetParser  {

    @Nullable
    Tweet get(String line);

}
