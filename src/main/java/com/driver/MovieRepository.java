package com.driver;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String,Movie> dbMovie = new HashMap<>();
    HashMap<String,Director>dbDirector = new HashMap<>();
    HashMap<String, List<String>> dbPair = new HashMap<>();

    public void addMovie(Movie movie){
        dbMovie.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        dbDirector.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie, String director){

        List<String> list = new LinkedList<>();

        if(dbPair.containsKey(director)){
            list = dbPair.get(director);
            list.add(movie);
            dbPair.put(director,list);
        }else{
            list.add(movie);
            dbPair.put(director,list);
        }

    }

    public Movie getMovieByName(String movieName){
        return dbMovie.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return dbDirector.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return dbPair.get(directorName);
    }

    public List<String> findAllMovies(){
        List<String> movies = new LinkedList<>();

        for(String movie : dbMovie.keySet()){
            movies.add(movie);
        }

        return movies;

    }


    public void deleteDirectorByName(String directorName){

        if(dbPair.containsKey(directorName)){
            List<String> movies = dbPair.get(directorName);

            for(String movie : movies){
                dbMovie.remove(movie);
            }

            dbPair.remove(directorName);
        }


        dbDirector.remove(directorName);

    }

    public void deleteAllDirectors(){

        for(String directorName : dbDirector.keySet()){

            if(dbPair.containsKey(directorName)){

                for(String movie : dbPair.get(directorName)){
                    dbMovie.remove(movie);
                }
                dbPair.remove(directorName);
            }

        }

        dbDirector.clear();

    }






}
