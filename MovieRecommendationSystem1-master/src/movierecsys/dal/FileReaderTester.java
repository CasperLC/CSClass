/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        RatingDAO ratingDAO = new RatingDAO();
        UserDAO userDAO = new UserDAO();
        MovieDAO movieDao = new MovieDAO();
        MovieDBDAO movieDB = new MovieDBDAO();
        UserDBDAO userDB = new UserDBDAO();
//        mitigateMovies();
//        mitigateUsers();
//        mitigateRatings();
//        movieDB.deleteMovie(new Movie(17773,2001,"Test Movie"));
//        movieDB.updateMovie(new Movie(3,1997,"Character"));
//        movieDB.createMovie(2004, "Test Movie4");
//        movieDB.getMovie(1);
//        userDB.getAllUsers();
        userDB.updateUser(new User(7,"Test Update"));

    }

    public static void mitigateMovies() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        MovieDAO mvDao = new MovieDAO();
        List<Movie> movies = mvDao.getAllMovies();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            for (Movie movy : movies)
            {
                String sqlString = "INSERT INTO Movie(id, year, title) VALUES("
                        + movy.getId() + ","
                        + movy.getYear() + ",'"
                        + movy.getTitle().replace("'", "") + "');";

                statement.executeUpdate(sqlString);
                System.out.println("Affected row = " + 1);

            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

     public static void mitigateRatings() throws IOException
    {
        List<Rating> allRatings = new RatingDAO().getAllRatings();
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            int counter = 0;
            for (Rating rating : allRatings)
            {
                String sql = "INSERT INTO Rating (movieId, userId, rating) VALUES ("
                        + rating.getMovie() + ","
                        + rating.getUser() + ","
                        + rating.getRating()
                        + ");";
                st.addBatch(sql);
                counter++;
                if (counter % 1000 == 0)
                {
                    st.executeBatch();
                    System.out.println("Added 1000 ratings.");
                }
            }
            if (counter % 1000 != 0)
            {
                st.executeBatch();
                System.out.println("Added final batch of ratings.");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void mitigateUsers() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        UserDAO userDao = new UserDAO();
        List<User> users = userDao.getAllUsers();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            int counter = 0;
            for (User user : users)
            {
                String sqlString = "INSERT INTO UserList (id, name) VALUES("
                        + user.getId() + ",'"
                        + user.getName() + "');";
                statement.addBatch(sqlString);
                counter++;
                if (counter % 1000 == 0)
                {
                    statement.executeBatch();
                    System.out.println("Added 1000 users");
                }

//                statement.executeUpdate(sqlString);
//                System.out.println("Affected row = " + 1);
            }
            if (counter % 1000 != 0)
            {
                statement.executeBatch();
                System.out.println("Added final batch of users");
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

//        movieDB.getAllMovies();
//        ratingDAO.createRating(new Rating(17764, 123456, 3));
//        ratingDAO.createRating(new Rating(17765, 123456, 1));
//        ratingDAO.createRating(new Rating(17766, 123456, -5));
//        ratingDAO.updateRating(new Rating(17769, 123456, 3));
//        ratingDAO.deleteRating(new Rating(17769,123456,5));
//        List<Rating> userOneRate = ratingDAO.getRatings(new User(123456,"Test User"));
//        for (Rating rating : userOneRate) {
//            System.out.println(""+rating.toString());
//        }
//            
//        List<Rating> allRates = ratingDAO.getAllRatings();
//        for (Rating allRate : allRates) {
//            System.out.println(""+allRate.getRating());
//        }
//       List<User> allUse = userDAO.getAllUsers();
//       userDAO.createUser("TestUser");
//        userDAO.updateUser(new User(6, "TestUser: Now Updated"));
//        userDAO.deleteUser(new User(6,"TestUser: Now Updated"));
//        
//        for (User user : allUse) {
//            System.out.println(user.toString());
//        }
//        System.out.println("User count: " + allUse.size());
//        System.out.println("next user id " +userDAO.getNextAvailableUserId());
//       System.out.println("Specific user request: "+userDAO.getUser(2649120));
//        List<Movie> allMovs = movieDao.getAllMovies();
//
//        for (Movie allMov : allMovs)
//        {
//            System.out.println(allMov.getTitle());
//        }
//        System.out.println("Movie count: " + allMovs.size());
//        System.out.println("next id " +movieDao.getNextAvailableId());
//        System.out.println("The movie you requested is: " + movieDao.movieToString(movieDao.getMovie(17764)));
//        
}
