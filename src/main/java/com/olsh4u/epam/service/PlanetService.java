package com.olsh4u.epam.service;

import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.exception.ServiceException;

import java.util.List;

/**
 * The service interface for {@link Planet}, all method throws {@link ServiceException}.
 */
public interface PlanetService {

    /**
     * Find all planets.
     *
     * @return the list of planets
     * @throws ServiceException if the method failed
     */
    List<Planet> findAll() throws ServiceException;

    /**
     * Find planet by title.
     *
     * @param planetTitle the planet title
     * @return the planet
     * @throws ServiceException if the method failed
     */
    Planet findByTitle(String planetTitle) throws ServiceException;

    /**
     * Find planet page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of planets
     * @throws ServiceException if the method failed
     */
    List<Planet> findPlanetPageByPage(int page, int limit) throws ServiceException;

    /**
     * Count of all planets.
     *
     * @return number of all planets
     * @throws ServiceException if the method failed
     */
    int countAllPlanets() throws ServiceException;

    /**
     * Delete planet.
     *
     * @param id the id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save planet.
     *
     * @param planet the planet
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Planet planet) throws ServiceException;
}
