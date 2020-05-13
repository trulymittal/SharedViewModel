package com.example.sharedviewmodel;

public class Movie {

    private String name;
    private String rating;

    public Movie(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
