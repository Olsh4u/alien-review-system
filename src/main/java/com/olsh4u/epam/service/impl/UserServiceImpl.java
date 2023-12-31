package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.factory.DaoFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.User;
import com.olsh4u.epam.service.UserService;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.validation.impl.UserValidationImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;

/**
 * Implementation of {@link UserService} interface. Provides access to {@link com.olsh4u.epam.dao.UserDao}
 * and provides support for working with the entity {@link User}.
 *
 * @see Transaction
 * @see DaoException
 */
public class UserServiceImpl implements UserService {

    /**
     * Validator for this Service.
     */
    private static final Validation<User> VALIDATOR = new UserValidationImpl();

    /**
     * Find user by login.
     *
     * @param login the user login
     * @return the user and null if does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public User findByLogin(final String login) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            User result = DaoFactory.getInstance().getUserDao(transaction).findByLogin(login);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return true if user was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final User user) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(user);
            if (errors.isEmpty()) {
                if (user.getId() == 0) {
                    user.setPassword(ServiceFactory.getInstance()
                            .getSecurityService().hashPw(user.getPassword(), BCrypt.gensalt()));
                    result = DaoFactory.getInstance().getUserDao(transaction).create(user);
                } else {
                    if (user.getPassword() == null) {
                        User newUser = DaoFactory.getInstance().getUserDao(transaction).findByLogin(user.getLogin());
                        user.setPassword(newUser.getPassword());
                        result = DaoFactory.getInstance().getUserDao(transaction).update(user);
                    } else {
                        user.setPassword(ServiceFactory.getInstance()
                                .getSecurityService().hashPw(user.getPassword(), BCrypt.gensalt()));
                        result = DaoFactory.getInstance().getUserDao(transaction).update(user);
                    }
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid data", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
