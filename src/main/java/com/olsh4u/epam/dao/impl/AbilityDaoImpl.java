package com.olsh4u.epam.dao.impl;

import com.olsh4u.epam.dao.AbilityDao;
import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandler;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandlerFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link AbilityDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Ability}.
 *
 * @see Transaction
 * @see DaoException
 */
public class AbilityDaoImpl implements AbilityDao {
    private static final String UPDATE_ABILITY = "UPDATE abilities SET title = ? WHERE id = ?";
    private static final String CREATE_ABILITY = "INSERT INTO abilities VALUES (DEFAULT, ?)";
    private static final String DELETE_ABILITY_BY_ID = "DELETE FROM abilities WHERE id = ?";
    private static final String FIND_ABILITY_BY_ID = "SELECT id, title FROM abilities WHERE id = ?";
    private static final String COUNT_ALL_ABILITY = "SELECT COUNT(*) FROM abilities";
    private static final String FIND_ABILITY_PAGE_BY_PAGE = "SELECT id, title FROM abilities ORDER BY title LIMIT ? OFFSET ?";
    private static final String FIND_ALL_ABILITY = "SELECT id, title FROM abilities";
    private static final String FIND_ABILITY_BY_TITLE = "SELECT id, title FROM abilities WHERE title = ?";

    /**
     * An object that provides access to a data source.
     */
    private final Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Ability} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Ability> ABILITY_RESULT_SET_HANDLER = new ResultSetHandler<Ability>() {
        @Override
        public Ability handle(final ResultSet rs) throws DaoException {
            Ability ability = new Ability();
            try {
                ability.setId(rs.getInt("id"));
                ability.setTitle(rs.getString("title"));
                return ability;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public AbilityDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Find ability by title.
     *
     * @param abilityTitle the ability title
     * @return the ${@link Ability} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Ability findByTitle(final String abilityTitle) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ABILITY_BY_TITLE,
                ResultSetHandlerFactory.getSingleResultSetHandler(ABILITY_RESULT_SET_HANDLER), abilityTitle);
    }

    /**
     * Find all abilities.
     *
     * @return the list of abilities and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Ability> findAll() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALL_ABILITY,
                ResultSetHandlerFactory.getListResultSetHandler(ABILITY_RESULT_SET_HANDLER));
    }

    /**
     * Find abilities page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of abilities and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Ability> findAbilitiesPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_ABILITY_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(ABILITY_RESULT_SET_HANDLER), limit, offset);
    }

    /**
     * Count all abilities.
     *
     * @return number of all abilities
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllAbilities() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_ABILITY,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    /**
     * Find ability by id.
     *
     * @param id the ability id
     * @return the ${@link Ability} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Ability findById(final String id) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ABILITY_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(ABILITY_RESULT_SET_HANDLER), id);
    }

    /**
     * Delete abilities by id.
     *
     * @param id the ability id
     * @return true if ability was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), DELETE_ABILITY_BY_ID, id);
    }

    /**
     * Create ability.
     *
     * @param entity the ability
     * @return true if ability was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Ability entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), CREATE_ABILITY, entity.getTitle());
    }

    /**
     * Update ability.
     *
     * @param entity the ability
     * @return true if ability was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Ability entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), UPDATE_ABILITY, entity.getTitle(), entity.getId());
    }
}
