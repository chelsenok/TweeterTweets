package search;

import twitter4j.Status;

public interface SearchListener {

    void onStatusesReady(Status status);

}
