package com.joke;

import java.util.Random;

public class Joker {

    private static final Joke[] jokes = new Joke[] {
            new Joke("Why don't jokes work in octal?", "Because 7 10 11."),
            new Joke("Why are assembly programmers always soaking wet?", "They work below C-level."),
            new Joke("Why did the programmer quit his job?", "Because he didn't get arrays."),
            new Joke("How do you learn recursion", "first you learn recursion."),
            };

    public Joke getJoke(){
        return jokes[new Random().nextInt(jokes.length)];
    }
}