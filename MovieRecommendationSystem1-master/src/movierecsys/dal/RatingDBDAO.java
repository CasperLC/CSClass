/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author Caspe
 */
public class RatingDBDAO implements IRatingList
{

    @Override
    public void createRating(Rating rating) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        String movieIdString = "'" + rating.getMovie() + "'";
        String userIdString = "'" + rating.getUser() + "'";
        String ratingString = "'" + rating.getRating() + "'";

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();

            String sqlString = "INSERT INTO Rating(movieId,userId,rating) VALUES("
                    + movieIdString + ","
                    + userIdString + ","
                    + ratingString + ");";
            statement.executeUpdate(sqlString);
            System.out.println("Rating added");

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteRating(Rating rating) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");
        String sqlQuery = "DELETE FROM Rating WHERE movieId=" + rating.getMovie() + "AND userId=" + rating.getUser();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next())
            {
//                int id = rs.getInt("id");
//
//                System.out.println("Deleted movie with ID: " + id);

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Rating> getAllRatings() throws FileNotFoundException, IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        List<Rating> ratings = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Rating");
            while (rs.next())
            {
                int movieId = rs.getInt("movieId");
                int userId = rs.getInt("userId");
                int rating = rs.getInt("rating");
                ratings.add(new Rating(movieId, userId, rating));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatings(User user) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");


        List<Rating> ratingsUser = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sqlString = "SELECT * FROM Rating WHERE userId=" + user.getId();
            ResultSet rs = statement.executeQuery(sqlString);
            while (rs.next())
            {
                int movieId = rs.getInt("movieId");
                int userId = rs.getInt("userId");
                int rating = rs.getInt("rating");
                Rating theRating = new Rating(movieId, userId, rating);
                ratingsUser.add(theRating);
                
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ratingsUser;
    }

    @Override
    public void sortRatings() throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRating(Rating rating) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("UPDATE Rating SET rating="
                    + "'" + rating.getRating() + "'"
                    + "WHERE movieId=" + rating.getMovie()
                    + "AND userId="+rating.getUser());
            while (rs.next())
            {
//                int userId = rs.getInt("userId");
//                int movieId = rs.getInt("movieId");
//                String title = rs.getNString("title");

//                System.out.println("" + id);

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
