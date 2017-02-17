import search.Search;

public class Main {

    public static void main(String[] args) {

        Search search = new Search("2016-02-10", "2016-02-12", "friend");
        long time = System.currentTimeMillis();
        search.start();
        System.out.println("Hello World!  " + (System.currentTimeMillis() - time));
    }
}
