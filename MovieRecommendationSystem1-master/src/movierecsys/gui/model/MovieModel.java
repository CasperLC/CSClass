/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import movierecsys.be.Movie;
import movierecsys.bll.MRSManager;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.bll.exception.MovieRecSysException;

/**
 *
 * @author Caspe
 */
public class MovieModel
{

    private ObservableList<Movie> movies;

    private OwsLogicFacade logiclayer;

    public MovieModel() throws MovieRecSysException
    {
        movies = FXCollections.observableArrayList();
        logiclayer = new MRSManager();
        movies.addAll(logiclayer.getAllMovies());

    }

    public void createMovie(int year, String title) throws MovieRecSysException
    {
        logiclayer.createMovie(year, title);
    }

    public ObservableList<Movie> getSearch(String tekst) throws MovieRecSysException
    {

        ObservableList<Movie> searchM;
        searchM = FXCollections.observableArrayList();
        searchM.addAll(logiclayer.searchMovies(tekst));
//        searchM.add(new Movie(9999, 2010, "Test"));

        return searchM;
    }

    public ObservableList<Movie> getMovies()
    {
        return movies;
    }
}
