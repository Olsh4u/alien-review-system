package com.olsh4u.epam.dao.factory;

import com.olsh4u.epam.dao.*;
import com.olsh4u.epam.dao.impl.*;

/**
 * Class for accessing a specific DAO. Represents an implementation of the Singleton design pattern.
 *
 * @see Transaction
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private DaoFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }


    /**
     * Gets user dao.
     *
     * @param transaction the transaction
     * @return the user dao
     */
    public UserDao getUserDao(final Transaction transaction) {
        return new UserDaoImpl(transaction);
    }

    /**
     * Gets alien dao.
     *
     * @param transaction the transaction
     * @return the alien dao
     */
    public AlienDao getAlienDao(final Transaction transaction) {
        return new AlienDaoImpl(transaction);
    }

    /**
     * Gets source dao.
     *
     * @param transaction the transaction
     * @return the source dao
     */
    public SourceDao getSourceDao(final Transaction transaction) {
        return new SourceDaoImpl(transaction);
    }

    /**
     * Gets ability dao.
     *
     * @param transaction the transaction
     * @return the ability dao
     */
    public AbilityDaoImpl getAbilityDao(final Transaction transaction) {
        return new AbilityDaoImpl(transaction);
    }

    /**
     * Gets planet dao.
     *
     * @param transaction the transaction
     * @return the planet dao
     */
    public PlanetDaoImpl getPlanetDao(final Transaction transaction) {
        return new PlanetDaoImpl(transaction);
    }

    /**
     * Gets comment dao.
     *
     * @param transaction the transaction
     * @return the comment dao
     */
    public CommentDao getCommentDao(final Transaction transaction) {
        return new CommentDaoImpl(transaction);
    }
}
