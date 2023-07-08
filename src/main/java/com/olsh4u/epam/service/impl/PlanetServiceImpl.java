package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.factory.DaoFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.service.PlanetService;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.validation.impl.PlanetValidationImpl;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link PlanetService} interface. Provides access to {@link com.olsh4u.epam.dao.PlanetDao}
 * and provides support for working with the entity {@link Planet}.
 *
 * @see Transaction
 * @see DaoException
 */
public class PlanetServiceImpl implements PlanetService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Planet> VALIDATOR = new PlanetValidationImpl();

    /**
     * Find all planets.
     *
     * @return the {@link List} of ${@link Planet} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Planet> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Planet> result = DaoFactory.getInstance().getPlanetDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find planets page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Planet} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Planet> findPlanetsPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Planet> result = DaoFactory.getInstance().getPlanetDao(transaction).findPlanetsPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all planets.
     *
     * @return number of all planets
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllPlanets() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getPlanetDao(transaction).countAllPlanets();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find planet by title.
     *
     * @param planetTitle the planet title
     * @return the planet and null if it does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Planet findByTitle(final String planetTitle) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Planet result = DaoFactory.getInstance().getPlanetDao(transaction).findByTitle(planetTitle);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete planet.
     *
     * @param id the id
     * @return true if planet was made deleted and false otherwise.
     * @throws ServiceException if the method failed
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getPlanetDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save planet.
     *
     * @param planet the planet
     * @return true if planet was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Planet planet) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(planet);
            if (errors.isEmpty()) {
                if (planet.getId() == 0) {
                    result = DaoFactory.getInstance().getPlanetDao(transaction).create(planet);
                } else {
                    result = DaoFactory.getInstance().getPlanetDao(transaction).update(planet);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid planet", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}