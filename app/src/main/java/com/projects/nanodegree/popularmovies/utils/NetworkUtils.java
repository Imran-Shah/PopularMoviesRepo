package com.projects.nanodegree.popularmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.transferObjects.PopularMovies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Imran on 8/21/16.
 */
public final class NetworkUtils {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    public static PopularMoviesModel fetchData(String criteria) throws IOException {
        InputStream inputStream = null;
        BufferedReader reader;


        try {
            // URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=9947ddf3c3b054a7fd311569a1659650");

            URL url = new URL(getURL(criteria));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(Constants.REQUEST_METHOD);
            conn.setReadTimeout(Constants.READ_TIMEOUT);
            conn.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            if (response == 200) {
                inputStream = conn.getInputStream();
                if (inputStream != null) {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    Gson gson = new Gson();
                    PopularMovies popularMovies = gson.fromJson(reader, PopularMovies.class);
                    PopularMoviesModel popularMoviesModel = Converter.convert(popularMovies);
                    return popularMoviesModel;


                }
            }

        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();


        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return null;
    }


    public static String getURL(String selection) {
        StringBuffer buffer = new StringBuffer(Constants.API_BASE_URL);
        if (selection != null) {
            buffer.append(selection);
            buffer.append(Constants.API_KEY_PREFIX);
            buffer.append(Constants.API_KEY);
        }

        return buffer.toString();
    }

}
