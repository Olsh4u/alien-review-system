package com.olsh4u.epam.dao;


import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Planet;

import java.util.List;

/**
 * Dao interface for {@link Planet}, all method throws {@link DaoException}.
 */
public interface PlanetDao extends AbstractDao<String, Planet> {

    /**
     * Find planet by title.
     *
     * @param planetTitle the planet Title
     * @return the planet
     * @throws DaoException if the method failed
     */
    Planet findByTitle(String planetTitle) throws DaoException;

    /**
     * Find all planets.
     *
     * @return the list of planets
     * @throws DaoException if the method failed
     */
    List<Planet> findAll() throws DaoException;

    /**
     * Find planet page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of planets
     * @throws DaoException if the method failed
     */
    List<Planet> findPlanetPageByPage(int page, int limit) throws DaoException;

    /**
     * Count of all planet.
     *
     * @return number of all planets
     * @throws DaoException if the method failed
     */
    int countAllPlanets() throws DaoException;
}
