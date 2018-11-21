/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.bll.MRSManager;
import movierecsys.bll.OwsLogicFacade;
import movierecsys.bll.exception.MovieRecSysException;

/**
 *
 * @author Bruger
 */
public class MovieModel1 {

    private Movie selected;
    private User loggedInUser;
    private ObservableList<Movie> movies;
    private OwsLogicFacade logiclayer;

    public MovieModel1() throws MovieRecSysException {
        movies = FXCollections.observableArrayList();
        logiclayer = new MRSManager();
        movies.addAll(logiclayer.getAllMovies());
        loggedInUser = logiclayer.getUserById(7);

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

    public void rateMovie(int rating) {
        logiclayer.rateMovie(selected, loggedInUser, rating);
    }

    public void deleteRating() throws IOException {
        Rating deleted;
        int rating = specificRating().getRating();
        deleted = new Rating(selected.getId(), loggedInUser.getId(), rating);
        logiclayer.deleteRating(deleted);
    }

    public ObservableList<Rating> myRatings() {
        ObservableList<Rating> myRatings;
        myRatings = FXCollections.observableArrayList();
        try {
            myRatings.addAll(logiclayer.getMyRatings(loggedInUser));
        } catch (IOException ex) {
            Logger.getLogger(MovieModel1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myRatings;
    }

    public Rating specificRating() throws IOException {
        Rating specific = logiclayer.getSpecificRating(selected, loggedInUser);
        return specific;
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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User login) {
        this.loggedInUser = login;
    }

    public String getUsername() {
        return loggedInUser.getName();
    }

}
