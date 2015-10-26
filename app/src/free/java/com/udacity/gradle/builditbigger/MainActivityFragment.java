package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.joke.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends MainActivityBaseFragment {

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private Joke pendingJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        Log.d("MainFragment.free", ": onCreateView");
        mAdView = (AdView) root.findViewById(R.id.adView);
        requestNewBanner();

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                MainActivityFragment.super.loadJoke(pendingJoke);
            }
        });

        requestNewInterstitial();

        return root;
    }

    @Override
    protected void loadJoke(Joke joke) {
        //do not display ad if joke is null.
        if(joke == null) {
            super.loadJoke(null);
            return;
        }

        pendingJoke = joke;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            super.loadJoke(joke);
        }

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void requestNewBanner() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

    }
}
