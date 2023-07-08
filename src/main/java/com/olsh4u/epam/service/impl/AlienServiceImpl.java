package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.factory.DaoFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.*;
import com.olsh4u.epam.models.form.SearchForm;
import com.olsh4u.epam.service.AlienService;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.transaction.TransactionBuilder;
import com.olsh4u.epam.service.transaction.TransactionBuilderFactory;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.validation.impl.AlienValidationImpl;
import com.olsh4u.epam.utils.TransactionUtil;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link Alien} interface. Provides access to {@link com.olsh4u.epam.dao.AlienDao}
 * and provides support for working with the entity {@link Alien}.
 *
 * @see Transaction
 * @see DaoException
 */
public class AlienServiceImpl implements AlienService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Alien> VALIDATOR = new AlienValidationImpl();

    /**
     * Implementation of {@link TransactionBuilder} functional interface.
     * Needs for build {@link Alien} from different DAO classes.
     */
    private static final TransactionBuilder<Alien> ALIEN_TRANSACTION_HANDLER = (t, entity) -> {
        try {
            if (entity == null) {
                throw new ServiceException("Alien does not exist", ServiceException.NOT_FOUND);
            }
            List<Source> sourceList = DaoFactory.getInstance().getSourceDao(t).findSourceByAlienId(String.valueOf(entity.getId()));
            entity.setSourceList(sourceList);

            Ability ability = DaoFactory.getInstance().getAbilityDao(t).findById(String.valueOf(entity.getAbility().getId()));
            entity.getAbility().setTitle(ability.getTitle());

            Planet planet = DaoFactory.getInstance().getPlanetDao(t).findById(String.valueOf(entity.getPlanet().getId()));
            entity.getPlanet().setTitle(planet.getTitle());

            List<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForAlien(String.valueOf(entity.getId()));

            entity.setCommentList(commentSet);
            return entity;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    };

    /**
     * Find all aliens.
     *
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findAll();
            alienList = TransactionUtil.select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find alien page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> findAlienPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findAliensPageByPage(page, limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find most liked alien.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> findMostLikedAliens(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findMostLikedAlien(page, limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Latest alien.
     *
     * @param limit the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> latestAlien(final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).latestAlien(limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all aliens.
     *
     * @return number of all aliens
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllAliens() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getAlienDao(transaction).countAllAliens();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find alien by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     * @see SearchForm
     */
    @Override
    public List<Alien> findAlienBySearchForm(final SearchForm searchForm,
                                               final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (searchForm.getQuery() == null) {
                searchForm.setQuery("");
            }
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findAlienBySearchForm(searchForm, page, limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all aliens by search form.
     *
     * @param searchForm the search form
     * @return number of all aliens by search form
     * @throws ServiceException if there is an error on the DAO layer
     * @see SearchForm
     */
    @Override
    public int countAllAliensBySearchForm(final SearchForm searchForm) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getAlienDao(transaction).countAllAliensBySearchForm(searchForm);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find aliens that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> findAliensThatUserWatch(final String userId,
                                                 final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findAliensThatUserWatch(userId, page, limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all aliens that user watch.
     *
     * @param userId the user id
     * @return number of aliens that is watch user
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllAliensThatUserWatch(final String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getAlienDao(transaction).countAllAliensThatUserWatch(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find aliens that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the {@link List} of ${@link Alien} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Alien> findAliensThatUserLiked(final String userId,
                                                 final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Alien> alienList = DaoFactory.getInstance().getAlienDao(transaction).findAliensThatUserLiked(userId, page, limit);
            alienList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alienList);
            transaction.commit();
            return alienList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all aliens that user liked.
     *
     * @param userId the user id
     * @return number of aliens that liked user
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllAliensThatUserLiked(final String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getAlienDao(transaction).countAllAliensThatUserLiked(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * To watch alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if there is an error on the DAO layer or if the user is already watching this alien
     */
    @Override
    public void toWatchAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (!DaoFactory.getInstance().getAlienDao(transaction).userWatchThisAlien(alienId, userId)) {
                DaoFactory.getInstance().getAlienDao(transaction).toWatchAlien(userId, alienId);
                transaction.commit();
            } else {
                throw new ServiceException("Alien already watching", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Stop watch alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if there is an error on the DAO layer or if the user does not watch the alien
     */
    @Override
    public void stopWatchAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getAlienDao(transaction).userWatchThisAlien(alienId, userId)) {
                DaoFactory.getInstance().getAlienDao(transaction).stopWatchAlien(userId, alienId);
                transaction.commit();
            } else {
                throw new ServiceException("Alien does not watch", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Check if the user has seen this alien
     *
     * @param alienId the alien id
     * @param userId  the user id
     * @return true if user has seen this alien and false otherwise.
     * @throws ServiceException if the method failed
     */
    @Override
    public boolean userSeenThisAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getAlienDao(transaction)
                    .userWatchThisAlien(alienId, userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Check whether the user likes this alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if user liked this alien and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean userLikedThisAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getAlienDao(transaction)
                    .userLikedThisAlien(userId, alienId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Add a alien to user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if there is an error on the DAO layer or if the user is already liked this alien
     */
    @Override
    public void likedAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (!DaoFactory.getInstance().getAlienDao(transaction).userLikedThisAlien(userId, alienId)) {
                DaoFactory.getInstance().getAlienDao(transaction)
                        .likeAlien(userId, alienId);
                transaction.commit();
            } else {
                throw new ServiceException("Alien already liked", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Remove a alien to user liked.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @throws ServiceException if there is an error on the DAO layer or if the user does not like the alien
     */
    @Override
    public void dislikeAlien(final String userId, final String alienId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getAlienDao(transaction).userLikedThisAlien(userId, alienId)) {
                DaoFactory.getInstance().getAlienDao(transaction)
                        .dislikeAlien(userId, alienId);
                transaction.commit();
            } else {
                throw new ServiceException("Alien does not like", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find alien by id.
     *
     * @param id the alien id
     * @return the alien
     * @throws ServiceException if there is an error on the DAO layer or alien does not exist
     */
    @Override
    public Alien findById(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Alien alien = DaoFactory.getInstance().getAlienDao(transaction).findById(id);
            alien = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alien);
            transaction.commit();
            return alien;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find alien by name.
     *
     * @param name the alien name
     * @return the alien
     * @throws ServiceException if there is an error on the DAO layer or alien does not exist
     */
    @Override
    public Alien findByName(String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Alien alien = DaoFactory.getInstance().getAlienDao(transaction).findAlienByName(name);
            alien = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(ALIEN_TRANSACTION_HANDLER), alien);
            transaction.commit();
            return alien;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete alien.
     *
     * @param id the id
     * @return true if alien was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getAlienDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save alien.
     *
     * @param alien the alien
     * @return true if alien was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Alien alien) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(alien);
            if (errors.isEmpty()) {
                if (alien.getId() == 0) {
                    result = DaoFactory.getInstance().getAlienDao(transaction).create(alien);
                } else {
                    result = DaoFactory.getInstance().getAlienDao(transaction).update(alien);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Alien field not valid", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
