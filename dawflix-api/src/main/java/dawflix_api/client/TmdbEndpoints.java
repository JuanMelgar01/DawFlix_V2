package dawflix_api.client;

public final class TmdbEndpoints {

    public static final String POPULAR = "/movie/popular";
    public static final String MOVIE_BY_ID = "/movie/{movieId}";
    public static final String TOP_RATED = "/movie/top_rated";
    public static final String NOW_PLAYING = "/movie/now_playing";
    public static final String UPCOMING = "/movie/upcoming";

    private TmdbEndpoints() {}
}
