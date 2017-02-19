package search;

public class SearchLocal {

    public enum Query {
        MyLife,
        Obama,
    }

    private Query mQuery;
    private SearchListener mListener;

    public SearchLocal(Query query, SearchListener listener) {
        mQuery = query;
        mListener = listener;
    }

    public void start() {

    }

}
