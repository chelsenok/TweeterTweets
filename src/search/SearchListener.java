package search;

import twitter4j.Status;

import java.util.List;

public interface SearchListener {

    void onStatusesReady(List<Status> status, String from);

}
