package com.olsh4u.epam.dao;

import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Ability;

import java.util.List;

/**
 * Dao interface for {@link Ability}, all method throws {@link DaoException}.
 */
public interface AbilityDao extends AbstractDao<String, Ability> {

    /**
     * Find ability by title.
     *
     * @param abilityTitle the ability title
     * @return the ability
     * @throws DaoException if the method failed
     */
    Ability findByTitle(String abilityTitle) throws DaoException;

    /**
     * Find all abilities.
     *
     * @return the list of abilities
     * @throws DaoException if the method failed
     */
    List<Ability> findAll() throws DaoException;

    /**
     * Find ability page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of abilities
     * @throws DaoException if the method failed
     */
    List<Ability> findAbilitiesPageByPage(int page, int limit) throws DaoException;

    /**
     * Count of all abilities.
     *
     * @return number of all abilities
     * @throws DaoException if the method failed
     */
    int countAllAbilities() throws DaoException;
}
