/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import movierecsys.be.Movie;

/**
 *
 * @author Caspe
 */
public interface IMovieRepository
{

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     * @throws java.io.IOException
     */
    Movie createMovie(int releaseYear, String title) throws IOException;

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     * @throws java.io.IOException
     */
    void deleteMovie(Movie movie) throws IOException;

    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     * @throws java.io.FileNotFoundException
     */
    List<Movie> getAllMovies() throws FileNotFoundException, IOException;

    /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     */
    Movie getMovie(int id) throws IOException;

    /**
     * Gets the lowest available id from the movie source file.
     *
     * @return lowest available id
     * @throws java.io.IOException
     */
    int getNextAvailableId() throws IOException;

    /**
     * Takes a movie object and converts its variables into string
     *
     * @param movie
     * @return a string consisting of a movie object's id,releaseYear,title
     */
    String movieToString(Movie movie);

    /**
     * Sorts the movie_titles.txt file so that the movie id's are always in an
     * ascending order.
     *
     * @throws IOException
     */
    void sortMovies() throws IOException;

    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     */
    void updateMovie(Movie movie) throws IOException;
    
}
