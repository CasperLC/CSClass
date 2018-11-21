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

/**
 *
 * @author Caspe
 */
public class MovieDBDAO implements IMovieRepository
{

    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        int id = -1;
        String titleString = "'" + title + "'";
        String yearString = "'" + releaseYear + "'";
        String idString = "";

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();

            idString = "" + getNextAvailableId();

            String sqlString = "INSERT INTO Movie(id,year,title) VALUES("
                    + idString + ","
                    + releaseYear + ","
                    + titleString + ");";
            statement.executeUpdate(sqlString);
            System.out.println("Movie added with the ID: " + idString);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");
        String sqlQuery = "DELETE FROM Movie WHERE id=" + movie.getId();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next())
            {
                int id = rs.getInt("id");

                System.out.println("Deleted movie with ID: " + id);

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAllMovies() throws FileNotFoundException, IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        List<Movie> movies = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie");
            while (rs.next())
            {
                int id = rs.getInt("id");
                int year = rs.getInt("year");
                String title = rs.getNString("title");
                movies.add(new Movie(id, year, title));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        Movie theMovie = new Movie(0,0,"");

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sqlString = "SELECT * FROM Movie WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sqlString);
            while (rs.next())
            {
                int year = rs.getInt("year");
                String title = rs.getNString("title");
                theMovie = new Movie(id, year, title);
                System.out.println(""+theMovie.toString());
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return theMovie;
    }

    @Override
    public int getNextAvailableId() throws IOException
    {
        int availableId = 1;
        int lastCounted = 0;

        List<Movie> allMoviesNextId = getAllMovies();

        for (int i = 0; i < allMoviesNextId.size(); i++)
        {
            if (availableId == allMoviesNextId.get(i).getId())
            {
                availableId = 1;
            }
            if (availableId != 1 && allMoviesNextId.get(i).getId() == allMoviesNextId.size() - 1)
            {
                return availableId;
            } else if (availableId < allMoviesNextId.get(i).getId() && allMoviesNextId.get(i).getId() - 1 != lastCounted)
            {
                boolean free = true;
                for (Movie movie : allMoviesNextId)
                {

                    if (movie.getId() == lastCounted+1)
                    {
                        availableId = 1;
                        free = false;
                    }
                }
                if(free=true){
                    availableId = allMoviesNextId.get(i).getId() - 1;
                }
                
                return availableId;
            } else if (availableId == 1 && allMoviesNextId.get(i).getId() == allMoviesNextId.size())
            {
                availableId = allMoviesNextId.size() + 1;
                return availableId;
            }
            lastCounted = allMoviesNextId.get(i).getId();
        }
        return availableId;
    }

    @Override
    public String movieToString(Movie movie)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sortMovies() throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("UPDATE Movie SET title="
                    + "'" + movie.getTitle() + "',"
                    + "year=" + movie.getYear()
                    + "WHERE id=" + movie.getId());
            while (rs.next())
            {
                int id = rs.getInt("id");
//                int year = rs.getInt("year");
//                String title = rs.getNString("title");

                System.out.println("" + id);

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}
