/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import movierecsys.be.User;

/**
 *
 * @author Caspe
 */
public interface IUserList
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
    User createUser(String name) throws IOException;

    /**
     * Deletes a user from the persistence storage.
     *
     * @param movie The user to delete.
     * @throws java.io.IOException
     */
    void deleteUser(User user) throws IOException;

    /**
     * Gets a list of all known users.
     * @return List of users.
     */
    List<User> getAllUsers() throws FileNotFoundException, IOException;

    /**
     * Gets the lowest available id from the movie source file.
     *
     * @return lowest available id
     * @throws java.io.IOException
     */
    int getNextAvailableUserId() throws IOException;

    /**
     * Gets a single User by its ID.
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    User getUser(int id) throws IOException;

    /**
     * Sorts the users.txt file so that the user id's are always in an
     * ascending order.
     *
     * @throws IOException
     */
    void sortUsers() throws IOException;

    /**
     * Updates a user so the persistence storage reflects the given User object.
     * @param user The updated user.
     */
    void updateUser(User user) throws IOException;
    
}
