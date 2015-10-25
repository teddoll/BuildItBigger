package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joke.Joke;
import com.teddoll.telljokelibrary.TellJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityBaseFragment extends Fragment {

    private View progress;

    public MainActivityBaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d("MainFragment.base", ": onCreateView");
        progress = root.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        root.findViewById(R.id.tell_joke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJokeClick();
            }
        });
        return root;
    }

    protected void onJokeClick() {
        progress.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskListener() {
            @Override
            public void onJoke(Joke joke) {
                progress.setVisibility(View.GONE);
                loadJoke(joke);

            }
        }).execute();


    }

    protected void loadJoke(Joke joke) {
        if(joke != null) {
            Intent jokeIntent = new Intent(getActivity(), TellJokeActivity.class);
            jokeIntent.putExtra(TellJokeActivity.EXTRA_JOKE, joke.joke);
            jokeIntent.putExtra(TellJokeActivity.EXTRA_ANSWER, joke.answer);
            getActivity().startActivity(jokeIntent);
        } else {
            Toast.makeText(getActivity(), "Error occured fetching joke", Toast.LENGTH_SHORT).show();
        }
    }
}
