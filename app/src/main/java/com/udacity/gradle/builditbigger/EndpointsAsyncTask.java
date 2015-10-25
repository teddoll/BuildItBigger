package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.joke.Joke;
import com.teddydoll.jokes.backend.jokeApi.JokeApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, Joke> {
    private static JokeApi myApiService = null;
    private final EndpointsAsyncTaskListener listner;


    public interface EndpointsAsyncTaskListener {
        void onJoke(Joke joke);
    }
    public EndpointsAsyncTask(EndpointsAsyncTaskListener listener) {
        this.listner = listener;
    }

    @Override
    protected Joke doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            com.teddydoll.jokes.backend.jokeApi.model.Joke joke =
                myApiService.getJoke().execute();
            return new Joke(joke.getJoke(), joke.getAnswer());
        } catch (IOException e) {
            return null;
        }
    }



    @Override
    protected void onPostExecute(Joke result) {
        if(listner != null) listner.onJoke(result);
    }
}
