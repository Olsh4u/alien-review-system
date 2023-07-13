package com.olsh4u.epam.service;


import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.models.form.SearchForm;

import java.util.List;

/**
 * The service interface for {@link Alien}, all method throws {@link ServiceException}.
 */
public interface AlienService {

    /**
     * Find alien by id.
     *
     * @param id the alien id
     * @return the alien
     * @throws ServiceException if the method failed
     */
    Alien findById(String id) throws ServiceException;

    /**
     * Find alien by name.
     *
     * @param name the alien name
     * @return the alien
     * @throws ServiceException if the method failed
     */
    Alien findByName(String name) throws ServiceException;

    /**
     * Find alien page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> findAlienPageByPage(int page, int limit) throws ServiceException;

    /**
     * Find all aliens.
     *
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> findAll() throws ServiceException;

    /**
     * Find most liked aliens.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> findMostLikedAliens(int page, int limit) throws ServiceException;

    /**
     * Latest alien.
     *
     * @param limit the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> latestAlien(int limit) throws ServiceException;

    /**
     * Find aliens that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> findAliensThatUserLiked(String userId, int page, int limit) throws ServiceException;

    /**
     * Find aliens by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     * @see SearchForm
     */
    List<Alien> findAlienBySearchForm(SearchForm searchForm, int page, int limit) throws ServiceException;

    /**
     * Find aliens that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens
     * @throws ServiceException if the method failed
     */
    List<Alien> findAliensThatUserWatch(String userId, int page, int limit) throws ServiceException;

    /**
     * Count of all aliens by search form.
     *
     * @param searchForm the search form
     * @return number of all aliens by search form
     * @throws ServiceException if the method failed
     * @see SearchForm
     */
    int countAllAliensBySearchForm(SearchForm searchForm) throws ServiceException;

    /**
     * Count of all aliens.
     *
     * @return number of all aliens
     * @throws ServiceException if the method failed
     */
    int countAllAliens() throws ServiceException;

    /**
     * Count of all aliens that user watch.
     *
     * @param userId the user id
     * @return number of aliens that is watch user
     * @throws ServiceException if the method failed
     */
    int countAllAliensThatUserWatch(String userId) throws ServiceException;

    /**
     * Check if the user has seen this alien
     *
     * @param alienId the alien id
     * @param userId  the user id
     * @return true if user has seen this alien and false otherwise.
     * @throws ServiceException if the method failed
     */
    boolean userSeenThisAlien(String userId, String alienId) throws ServiceException;

    /**
     * To watch alien.
     *
     * @param userId  the user id
     * @param alienId the alien id
     * @throws ServiceException if the method failed
     */
    void toWatchAlien(String userId, String alienId) throws ServiceException;

    /**
     * Stop watch alien.
     *
     * @param userId  the user id
     * @param alienId the alien id
     * @throws ServiceException if the method failed
     */
    void stopWatchAlien(String userId, String alienId) throws ServiceException;

    /**
     * Count of all aliens that user liked.
     *
     * @param userId the user id
     * @return number of aliens that liked user
     * @throws ServiceException if the method failed
     */
    int countAllAliensThatUserLiked(String userId) throws ServiceException;

    /**
     * Check if the user likes this alien.
     *
     * @param userId   the user id
     * @param alienId the aliens id
     * @return true if user liked that alien and false otherwise.
     * @throws ServiceException if the method failed
     */
    boolean userLikedThisAlien(String userId, String alienId) throws ServiceException;

    /**
     * Add an alien to user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if the method failed
     */
    void likeAlien(String userId, String alienId) throws ServiceException;

    /**
     * Remove an alien from user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if the method failed
     */
    void dislikeAlien(String userId, String alienId) throws ServiceException;

    /**
     * Delete alien.
     *
     * @param id the id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save alien.
     *
     * @param alien the alien
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save (Alien alien) throws ServiceException;
}
