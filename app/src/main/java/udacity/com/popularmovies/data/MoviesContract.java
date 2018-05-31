package udacity.com.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "udacity.com.popularmovies";
    public static final String PATH_MOVIES= "movies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /* Inner class that defines the table contents of the weather table */
    public static final class MovieEntry implements BaseColumns {

        /* The base CONTENT_URI used to query the Weather table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES)
                .build();

        /* Used internally as the name of our weather table. */
        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static String getSqlSelectMoviePosters() {
              return MoviesContract.MovieEntry.COLUMN_POSTER_PATH;
        }

        public static Uri buildMovieUriWithId(String movieId) {
            return CONTENT_URI.buildUpon()
                    .appendPath(movieId)
                    .build();
        }
    }
}
