package com.olsh4u.epam.service;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;

import java.util.List;

public interface AbilityService {

    /**
     * Find ability by title.
     *
     * @param abilityTitle the ability name
     * @return the ability
     * @throws ServiceException if the method failed
     */
    Ability findByTitle(String abilityTitle) throws ServiceException;

    /**
     * Find all abilities.
     *
     * @return the list of abilities
     * @throws ServiceException if the method failed
     */
    List<Ability> findAll() throws ServiceException;

    /**
     * Find ability page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of abilities
     * @throws ServiceException if the method failed
     */
    List<Ability> findAbilitiesPageByPage(int page, int limit) throws ServiceException;

    /**
     * Count all abilities.
     *
     * @return number of all abilities
     * @throws ServiceException if the method failed
     */
    int countAllAbilities() throws ServiceException;

    /**
     * Delete ability.
     *
     * @param id the ability id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save ability.
     *
     * @param ability the ability
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Ability ability) throws ServiceException;
}
