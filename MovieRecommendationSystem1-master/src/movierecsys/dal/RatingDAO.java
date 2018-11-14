/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class RatingDAO {

    private static final String RATING_SOURCE = "data/user_ratings";
    private static final String RATINGTXT_SOURCE = "data/ratings.txt";
    private static final int RECORD_SIZE = Integer.BYTES * 3;

        /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating) throws IOException
    {
        Path path = new File(RATINGTXT_SOURCE).toPath();

        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE))
        {
            bw.newLine();
            bw.write(rating.getMovie()+","+rating.getUser()+","+rating.getRating());
        }
        sortRatings();
    }
    
    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    public void updateRating(Rating rating) throws IOException
    {
        File tmp = new File("data/tmp_movies.txt");
        List<Rating> allRatings = getAllRatings();
        allRatings.removeIf((Rating t) -> t.getMovie() == rating.getMovie() && t.getUser() == rating.getUser());
        allRatings.add(rating);
        Collections.sort(allRatings, (Rating o1, Rating o2) -> Integer.compare(o1.getMovie(), o2.getMovie()));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp)))
        {
            for (Rating rate : allRatings)
            {
                bw.write(rate.getMovie() + "," + rate.getUser() + "," + rate.getRating());
                bw.newLine();
            }
        }
        Files.copy(tmp.toPath(), new File(RATINGTXT_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmp.toPath());
    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating) throws IOException
    {
        File inputFile = new File(RATINGTXT_SOURCE);
        File tempFile = new File("data/temp_ratings.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Rating dRating = rating;
        String dMovie = Integer.toString(dRating.getMovie());
        String dUser = Integer.toString(dRating.getUser());
        String dRate = Integer.toString(dRating.getRating());

        String lineToRemove = "" + dMovie + "," + dUser + "," + dRate;
        String currentLine;

        while ((currentLine = reader.readLine()) != null)
        {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove))
            {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
        sortRatings();
    }
    
    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings() throws FileNotFoundException, IOException
    {
        List<Rating> allRatings = new ArrayList<>();
        File file = new File(RATINGTXT_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                try
                {
                    Rating rate = stringArrayToRating(line);
                    allRatings.add(rate);
                } catch (Exception ex)
                {
                    //Do nothing we simply do not accept malformed lines of data.
                    //In a perfect world you should at least log the incident.
                }
            }
        }
        return allRatings;
    }
    
        private Rating stringArrayToRating(String t)
    {
        String[] arrRating = t.split(",");

        int mid = Integer.parseInt(arrRating[0]);
        int uid = Integer.parseInt(arrRating[1]);
        int rating = Integer.parseInt(arrRating[2]);

        Rating rate = new Rating(mid, uid, rating);
        return rate;
    }
    
    /**
     * Get all ratings from a specific user.
     * @param user The user 
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user) throws IOException
    {
        List<Rating> getRatingList = getAllRatings();
        List<Rating> userRatings = new ArrayList<>();
        for (Rating rating : getRatingList)
        {
            if (rating.getUser() == user.getId())
            {
                userRatings.add(rating);
            }
        }
        return userRatings;
    }
    
        /**
     * Sorts the ratings.txt file so that the movie id's are always in an
     * ascending order.
     *
     * @throws IOException
     */
    public void sortRatings() throws IOException
    {
        File tmp = new File("data/tmp.txt");
        List<Rating> sortRList = getAllRatings();

        Collections.sort(sortRList, Comparator.comparingInt(Rating::getMovie));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp)))
        {
            for (Rating rate : sortRList)
            {
                bw.write(rate.getMovie()+","+rate.getUser()+","+rate.getRating());
                bw.newLine();
            }
        }
        Files.copy(tmp.toPath(), new File(RATINGTXT_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmp.toPath());
    }

}
