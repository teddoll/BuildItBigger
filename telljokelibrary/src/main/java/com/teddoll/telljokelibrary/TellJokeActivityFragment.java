package com.teddoll.telljokelibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class TellJokeActivityFragment extends Fragment {

    public TellJokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tell_joke, container, false);
        String joke = getActivity().getIntent().getStringExtra(TellJokeActivity.EXTRA_JOKE);
        String answer = getActivity().getIntent().getStringExtra(TellJokeActivity.EXTRA_ANSWER);

        final TextView jokeView = (TextView) root.findViewById(R.id.joke);
        final TextView answerView = (TextView) root.findViewById(R.id.answer);
        jokeView.setText(joke);
        answerView.setText(answer);
        answerView.setVisibility(View.INVISIBLE);
        root.findViewById(R.id.answer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerView.setVisibility(View.VISIBLE);
            }
        });


        return root;
    }
}
