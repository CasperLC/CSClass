/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.dal.MovieDAO;
import movierecsys.dal.RatingDAO;
import movierecsys.dal.UserDAO;

/**
 *
 * @author Caspe
 */
public class MRSManager implements OwsLogicFacade {

    private final MovieDAO movieDAO;
    private final RatingDAO ratingDAO;
    private final UserDAO userDAO;

    public MRSManager() {
        movieDAO = new MovieDAO();
        userDAO = new UserDAO();
        ratingDAO = new RatingDAO();
    }

    @Override
    public List<Rating> getRecommendedMovies(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllMovies() throws MovieRecSysException {
        try {
            return movieDAO.getAllMovies();
        } catch (IOException ex) {
            throw new MovieRecSysException("Couldn't read all movies: " + ex.getMessage());
        }
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getMovieReccomendations(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> searchMovies(String query) throws MovieRecSysException {
        List<Movie> movieSearch = new ArrayList<>();
        try {
            List<Movie> allMovies = movieDAO.getAllMovies();
            if (query.isEmpty()) {
                movieSearch.addAll(allMovies);
            }
            for (Movie allMovy : allMovies) {
                if (query.toLowerCase().equals(allMovy.getTitle().toLowerCase())) {
                    movieSearch.add(new Movie(allMovy.getId(), allMovy.getYear(), allMovy.getTitle()));
                }

            }
        } catch (IOException ex) {
            throw new MovieRecSysException("Couldn't read all movies: " + ex.getMessage());
        }
        return movieSearch;
    }

    @Override
    public Movie createMovie(int year, String title) {
        
        Movie newMovie = null;
        try
        {
            newMovie = movieDAO.createMovie(year, title);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newMovie;
    }

    @Override
    public void updateMovie(Movie movie) {
        try
        {
            movieDAO.updateMovie(movie);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMovie(Movie movie) {
        try
        {
            movieDAO.deleteMovie(movie);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rateMovie(Movie movie, User user, int rating) {
        try
        {
            ratingDAO.createRating(new Rating(movie.getId(), user.getId(), rating));
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User createNewUser(String name) {
        User nUser = null;
        try
        {
            nUser = userDAO.createUser(name);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nUser;
    }

    @Override
    public User getUserById(int id) {
        User theUser = null;
        try
        {
            theUser = userDAO.getUser(id);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsersList = new ArrayList<>();
        try
        {
            allUsersList.addAll(userDAO.getAllUsers());
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsersList;
    }

}
