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
import movierecsys.be.User;

/**
 *
 * @author Caspe
 */
public class UserDBDAO implements IUserList
{

    @Override
    public User createUser(String name) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        int id = -1;
        String nameString = "'" + name + "'";
        String idString = "";

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();

            idString = "" + getNextAvailableUserId();

            String sqlString = "INSERT INTO User(id,name) VALUES("
                    + idString + ","
                    + nameString + ");";
            statement.executeUpdate(sqlString);
            System.out.println("User added with the ID: " + idString);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(User user) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");
        String sqlQuery = "DELETE FROM User WHERE id=" + user.getId();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next())
            {
                int id = rs.getInt("id");

                System.out.println("Deleted User with ID: " + user.getId());

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() throws FileNotFoundException, IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        List<User> users = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM UserList");
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                users.add(new User(id,name));
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public int getNextAvailableUserId() throws IOException
    {
        int availableId = 1;
        int lastCounted = 0;

        List<User> allUsersNextId = getAllUsers();

        for (int i = 0; i < allUsersNextId.size(); i++)
        {
            if (availableId == allUsersNextId.get(i).getId())
            {
                availableId = 1;
            }
            if (availableId != 1 && allUsersNextId.get(i).getId() == allUsersNextId.size() - 1)
            {
                return availableId;
            } else if (availableId < allUsersNextId.get(i).getId() && allUsersNextId.get(i).getId() - 1 != lastCounted)
            {
                boolean free = true;
                for (User user : allUsersNextId)
                {

                    if (user.getId() == lastCounted+1)
                    {
                        availableId = 1;
                        free = false;
                    }
                }
                if(free=true){
                    availableId = allUsersNextId.get(i).getId() - 1;
                }
                
                return availableId;
            } else if (availableId == 1 && allUsersNextId.get(i).getId() == allUsersNextId.size())
            {
                availableId = allUsersNextId.size() + 1;
                return availableId;
            }
            lastCounted = allUsersNextId.get(i).getId();
        }
        return availableId;
    }

    @Override
    public User getUser(int id) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        User theUser = new User(0,"");

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sqlString = "SELECT * FROM UserList WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sqlString);
            while (rs.next())
            {
                String name = rs.getNString("name");
                theUser = new User(id, name);
                System.out.println(""+theUser.toString());
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return theUser;
    }

    @Override
    public void sortUsers() throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSystem");
        ds.setUser("CS2018A_7");
        ds.setPassword("CS2018A_7");

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("UPDATE User SET name="
                    + "'" + user.getName() + "',"
                    + "WHERE id=" + user.getId());
            while (rs.next())
            {
                int id = rs.getInt("id");

                System.out.println("" + id);

            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
