package com.olsh4u.epam.dao;

import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.models.form.SearchForm;

import java.util.List;

/**
 * Dao interface for {@link Alien}, all method throws {@link DaoException}.
 */
public interface AlienDao extends AbstractDao<String, Alien> {

    /**
     * Find all alien.
     *
     * @return the list of aliens
     * @throws DaoException if the method failed
     */
    List<Alien> findAll() throws DaoException;

    /**
     * Find alien by alien name.
     *
     * @param name the name
     * @return the alien
     * @throws DaoException if the method failed
     */
    Alien findAlienByName(String name) throws DaoException;

    /**
     * Find alien page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens
     * @throws DaoException if the method failed
     */
    List<Alien> findAliensPageByPage(int page, int limit) throws DaoException;

    /**
     * Find most liked alien.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens
     * @throws DaoException if the method failed
     */
    List<Alien> findMostLikedAlien(int page, int limit) throws DaoException;

    /**
     * Latest alien.
     *
     * @param limit the limit
     * @return the list of alien
     * @throws DaoException if the method failed
     */
    List<Alien> latestAlien(int limit) throws DaoException;

    /**
     * Count of all aliens.
     *
     * @return number of all aliens
     * @throws DaoException if the method failed
     */
    int countAllAliens() throws DaoException;

    /**
     * Find alien by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of aliens
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    List<Alien> findAlienBySearchForm(SearchForm searchForm, int page, int limit) throws DaoException;

    /**
     * Count of all aliens by search form.
     *
     * @param searchForm the search form
     * @return number of all aliens by search form
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    int countAllAliensBySearchForm(SearchForm searchForm) throws DaoException;

    /**
     * To watch alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise
     * @throws DaoException if the method failed
     */
    boolean toWatchAlien(String userId, String alienId) throws DaoException;

    /**
     * Stop watch alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise
     * @throws DaoException if the method failed
     */
    boolean stopWatchAlien(String userId, String alienId) throws DaoException;

    /**
     * Check if the user is watching this alien.
     *
     * @param alienId the alien id
     * @param userId   the user id
     * @return true if user watch this alien and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean userWatchThisAlien(String alienId, String userId) throws DaoException;

    /**
     * Find aliens that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens
     * @throws DaoException if the method failed
     */
    List<Alien> findAliensThatUserWatch(String userId, int page, int limit) throws DaoException;

    /**
     * Count of all aliens that user watch.
     *
     * @param userId the user id
     * @return number of aliens that is watch user
     * @throws DaoException if the method failed
     */
    int countAllAliensThatUserWatch(String userId) throws DaoException;

    /**
     * Find aliens that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens
     * @throws DaoException if the method failed
     */
    List<Alien> findAliensThatUserLiked(String userId, int page, int limit) throws DaoException;

    /**
     * Count of all aliens that user liked.
     *
     * @param userId the user id
     * @return number of aliens that liked user
     * @throws DaoException if the method failed
     */
    int countAllAliensThatUserLiked(String userId) throws DaoException;

    /**
     * Add a alien to user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean likeAlien(String userId, String alienId) throws DaoException;

    /**
     * Remove an alien to user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean dislikeAlien(String userId, String alienId) throws DaoException;

    /**
     * Check if the user likes this alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if user liked this alien and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean userLikedThisAlien(String userId, String alienId) throws DaoException;

}
