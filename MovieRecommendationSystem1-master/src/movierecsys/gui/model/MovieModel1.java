/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.MRSManager;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.bll.exception.MovieRecSysException;

/**
 *
 * @author Bruger
 */

public class MovieModel1 {

    private Movie selected;
    private ObservableList<Movie> movies;
    private OwsLogicFacade logiclayer;

    public MovieModel1() throws MovieRecSysException {
        movies = FXCollections.observableArrayList();
        logiclayer = new MRSManager();
        movies.addAll(logiclayer.getAllMovies());

    }

    /**
     * Creates a new movie with automatic ID and the given parameters.
     *
     * @param year
     * @param title
     * @throws MovieRecSysException
     */
    public void createMovie(int year, String title) throws MovieRecSysException {
        logiclayer.createMovie(year, title);
    }

    /**
     * updates selected movie, movie ID stays the same and release+title changes
     * to what was given as the parameters.
     *
     * @param releaseYear
     * @param title
     */
    public void updateMovie(int releaseYear, String title) {
        int updatedId = selected.getId();
        Movie movie = new Movie(updatedId, releaseYear, title);
        logiclayer.updateMovie(movie);
    }

    public void deleteMovie() {
        Movie movieToRemove = getSelected();
        logiclayer.deleteMovie(movieToRemove);
    }

    /**
     *
     * @param tekst refers to text from the search TextField.
     * @return a list of movies that have the searched for title.
     * @throws MovieRecSysException
     */
    public ObservableList<Movie> getSearch(String tekst) throws MovieRecSysException {

        ObservableList<Movie> searchM;
        searchM = FXCollections.observableArrayList();
        searchM.addAll(logiclayer.searchMovies(tekst));
//        searchM.add(new Movie(9999, 2010, "Test"));

        return searchM;
    }

    /**
     * gets the default observable list of all movies.
     *
     * @return observable list of movies
     */
    public ObservableList<Movie> getMovies() {
        return movies;
    }

    /**
     * Get the value of selected
     *
     * @return the value of selected
     */
    public Movie getSelected() {
        return selected;
    }

    /**
     * Set the value of selected
     *
     * @param selected new value of selected
     */
    public void setSelected(Movie selected) {
        this.selected = selected;
    }

}
