package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.factory.DaoFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Source;
import com.olsh4u.epam.service.SourceService;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.validation.impl.SourceValidationImpl;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link SourceService} interface. Provides access to {@link com.olsh4u.epam.dao.SourceDao}
 * and provides support for working with the entity {@link Source}.
 *
 * @see Transaction
 * @see DaoException
 */
public class SourceServiceImpl implements SourceService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Source> VALIDATOR = new SourceValidationImpl();

    /**
     * Find source by title.
     *
     * @param title the source title
     * @return the source and null if it does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Source findByTitle(final String title) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Source result = DaoFactory.getInstance().getSourceDao(transaction).findByTitle(title);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find all sources.
     *
     * @return the {@link List} of ${@link Source} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Source> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Source> result = DaoFactory.getInstance().getSourceDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find sources page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Source} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Source> findSourcesPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Source> result = DaoFactory.getInstance().getSourceDao(transaction).findSourcesPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count all sources.
     *
     * @return number of all sources
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllSources() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSourceDao(transaction).countAllSources();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete source.
     *
     * @param id the source id
     * @return true if source was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSourceDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save source.
     *
     * @param source the source
     * @return true if source was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Source source) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(source);
            if (errors.isEmpty()) {
                if (source.getId() == 0) {
                    result = DaoFactory.getInstance().getSourceDao(transaction).create(source);
                } else {
                    result = DaoFactory.getInstance().getSourceDao(transaction).update(source);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid source", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
