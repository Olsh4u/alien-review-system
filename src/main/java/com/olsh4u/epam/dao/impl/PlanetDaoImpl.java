package com.olsh4u.epam.dao.impl;

import com.olsh4u.epam.dao.PlanetDao;
import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandler;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandlerFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * Implementation of {@link PlanetDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Planet}.
 *
 * @see Transaction
 * @see DaoException
 */
public class PlanetDaoImpl implements PlanetDao {

    private static final String COUNT_ALL_PLANETS = "SELECT COUNT(*) FROM planets";
    private static final String FIND_PLANET_BY_ID = "SELECT id, title FROM planets WHERE id = ?";
    private static final String UPDATE_PLANET = "UPDATE planet SET title = ? WHERE id = ?";
    private static final String FIND_PLANET_BY_TITLE = "SELECT id, title FROM planets WHERE title = ?";
    private static final String FIND_ALL_PLANETS = "SELECT id, title FROM planets";
    private static final String FIND_PLANETS_PAGE_BY_PAGE = "SELECT id, title FROM planets ORDER BY title LIMIT ? OFFSET ?";
    private static final String DELETE_PLANET_BY_ID = "DELETE FROM planets WHERE id = ?";
    private static final String CREATE_PLANET = "INSERT INTO planet VALUES (DEFAULT, ?)";

    /**
     * An object that provides access to a data source.
     */
    private final Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Planet} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Planet> PLANET_RESULT_SET_HANDLER = new ResultSetHandler<Planet>() {
        @Override
        public Planet handle(final ResultSet rs) throws DaoException {
            try {
                Planet planet = new Planet();
                planet.setId(rs.getInt("id"));
                planet.setTitle(rs.getString("title"));
                return planet;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };




    public PlanetDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Find planet by title.
     *
     * @param planetTitle the planet title
     * @return the ${@link Planet} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Planet findByTitle(final String planetTitle) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_PLANET_BY_TITLE,
                ResultSetHandlerFactory.getSingleResultSetHandler(PLANET_RESULT_SET_HANDLER), planetTitle);
    }

    /**
     * Find all planets.
     *
     * @return the list of planets and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Planet> findAll() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALL_PLANETS,
                ResultSetHandlerFactory.getListResultSetHandler(PLANET_RESULT_SET_HANDLER));
    }

    /**
     * Find planets page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of planets and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Planet> findPlanetsPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_PLANETS_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(PLANET_RESULT_SET_HANDLER), limit, offset);
    }

    /**
     * Count all planets.
     *
     * @return number of all planets
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllPlanets() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_PLANETS,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    /**
     * Find planet by id.
     *
     * @param id the planet id
     * @return the ${@link Planet} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Planet findById(final String id) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_PLANET_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(PLANET_RESULT_SET_HANDLER), id);
    }

    /**
     * Delete planet by id.
     *
     * @param id the planet id
     * @return true if planet was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), DELETE_PLANET_BY_ID, id);
    }

    /**
     * Create planet.
     *
     * @param entity the planet.
     * @return true if planet was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Planet entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), CREATE_PLANET, entity.getTitle());
    }

    /**
     * Update planet.
     *
     * @param entity the planet.
     * @return true if planet was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Planet entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), UPDATE_PLANET, entity.getTitle(), entity.getId());
    }
}