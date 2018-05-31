package udacity.com.popularmovies.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import udacity.com.popularmovies.data.MoviesContract;

public class OpenMoviesJsonUtils {

    private static final String OWM_MESSAGE_CODE = "cod";
    private static final String RESULTS = "results";

    private static final String VOTE_COUNT = "vote_count";
    private static final String ID = "id";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String TITLE = "title";
    private static final String POPULARITY = "popularity";
    private static final String POSTER_PATH = "poster_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String ORIGINAL_TITLE ="original_title";
    private static final String OVERVIEW ="overview";
    private static final String RELEASE_DATE= "release_date";

    public static ContentValues[] getMoviesContentValuesFromJson(Context context, String moviesJsonStr)
            throws JSONException {

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        /* Is there an error? */
        if (moviesJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray jsonMoviesArray = moviesJson.getJSONArray(RESULTS);

          ContentValues[] moviesContentValues = new ContentValues[jsonMoviesArray.length()];

        for (int i = 0; i < jsonMoviesArray.length(); i++) {

            String vote_count = jsonMoviesArray.getJSONObject(i).optString(VOTE_COUNT) ;
            String id = jsonMoviesArray.getJSONObject(i).optString(ID) ;
            String video = jsonMoviesArray.getJSONObject(i).optString(VIDEO) ;
            String vote_average = jsonMoviesArray.getJSONObject(i).optString(VOTE_AVERAGE) ;
            String title = jsonMoviesArray.getJSONObject(i).optString(TITLE) ;
            String popularity = jsonMoviesArray.getJSONObject(i).optString(POPULARITY) ;
            String poster_path = jsonMoviesArray.getJSONObject(i).optString(POSTER_PATH) ;
            String original_language = jsonMoviesArray.getJSONObject(i).optString(ORIGINAL_LANGUAGE) ;
            String original_title = jsonMoviesArray.getJSONObject(i).optString(ORIGINAL_TITLE) ;
            String overview = jsonMoviesArray.getJSONObject(i).optString(OVERVIEW) ;
            String release_date = jsonMoviesArray.getJSONObject(i).optString(RELEASE_DATE);

            ContentValues movieValues = new ContentValues();

            movieValues.put(MoviesContract.MovieEntry.COLUMN_VOTE_COUNT , vote_count);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_ID , id);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_VIDEO , video);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE , vote_average);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_TITLE , title);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_POPULARITY , popularity);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_POSTER_PATH , poster_path);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE , original_language);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE , original_title);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW , overview);
            movieValues.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE , release_date);

            moviesContentValues[i] = movieValues ;
        }

        return moviesContentValues;
    }
}
