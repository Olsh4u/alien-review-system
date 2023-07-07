package com.olsh4u.epam.service;

import com.olsh4u.epam.models.User;
import com.olsh4u.epam.exception.ServiceException;

/**
 * The service interface for {@link User},
 * all method throws {@link ServiceException}.
 */
public interface UserService {

    /**
     * Find user by login.
     *
     * @param login the user login
     * @return the user
     * @throws ServiceException if the method failed
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Save user.
     *
     * @param user the user
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(User user) throws ServiceException;
}