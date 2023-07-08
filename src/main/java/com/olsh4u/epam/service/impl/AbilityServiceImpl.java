package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.factory.DaoFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.service.AbilityService;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.validation.impl.AbilityValidationImpl;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link AbilityService} interface. Provides access to {@link com.olsh4u.epam.dao.AbilityDao}
 * and provides support for working with the entity {@link Ability}.
 *
 * @see Transaction
 * @see DaoException
 */
public class AbilityServiceImpl implements AbilityService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Ability> VALIDATOR = new AbilityValidationImpl();

    /**
     * Find ability by name.
     *
     * @param abilityTitle the ability title
     * @return the ability and null if it does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Ability findByTitle(final String abilityTitle) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Ability result = DaoFactory.getInstance().getAbilityDao(transaction).findByTitle(abilityTitle);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find all ability.
     *
     * @return the {@link List} of ${@link Ability} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Ability> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Ability> result = DaoFactory.getInstance().getAbilityDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find ability page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Ability} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Ability> findAbilitiesPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Ability> result = DaoFactory.getInstance().getAbilityDao(transaction).findAbilitiesPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count all abilities.
     *
     * @return number of all abilities
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllAbilities() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getAbilityDao(transaction).countAllAbilities();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete ability.
     *
     * @param id the ability id
     * @return true if ability was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getAbilityDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save ability.
     *
     * @param ability the ability
     * @return true if ability was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Ability ability) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(ability);
            if (errors.isEmpty()) {
                if (ability.getId() == 0) {
                    result = DaoFactory.getInstance().getAbilityDao(transaction).create(ability);
                } else {
                    result = DaoFactory.getInstance().getAbilityDao(transaction).update(ability);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid ability", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}