package com.olsh4u.epam.dao.impl;

import com.olsh4u.epam.dao.SourceDao;
import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandler;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandlerFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Source;
import com.olsh4u.epam.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link SourceDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Source}.
 *
 * @see Transaction
 * @see DaoException
 */
public class SourceDaoImpl implements SourceDao {

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Source} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Source> SOURCES_RESULT_SET_HANDLER = new ResultSetHandler<Source>() {
        @Override
        public Source handle(final ResultSet rs) throws DaoException {
            Source source = new Source();
            try {
                source.setId(rs.getInt("id"));
                source.setTitle(rs.getString("title"));
                return source;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public SourceDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_SOURCE_BY_TITLE = "SELECT id, title FROM sources WHERE source = ?";

    /**
     * Find source by title.
     *
     * @param title the source title
     * @return the ${@link Source} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Source findByTitle(final String title) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_SOURCE_BY_TITLE,
                ResultSetHandlerFactory.getSingleResultSetHandler(SOURCES_RESULT_SET_HANDLER), title);
    }

    private static final String FIND_ALL_SOURCES = "SELECT id, title FROM sources";

    /**
     * Find all sources.
     *
     * @return the list of sources and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Source> findAll() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALL_SOURCES,
                ResultSetHandlerFactory.getListResultSetHandler(SOURCES_RESULT_SET_HANDLER));
    }

    private static final String FIND_SOURCES_PAGE_BY_PAGE = "SELECT id, title FROM sources ORDER BY title LIMIT ? OFFSET ?";

    /**
     * Find sources page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of sources and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Source> findSourcesPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_SOURCES_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(SOURCES_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String COUNT_ALL_SOURCES = "SELECT COUNT(*) FROM sources";

    /**
     * Count all sources.
     *
     * @return number of all sources
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllSources() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_SOURCES,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    private static final String FIND_SOURCES_BY_ALIEN_ID = "SELECT g.id, g.title FROM sources g JOIN aliens_sources sg on g.id = sg.source_id where alien_id = ?";

    /**
     * Find sources by aliens id.
     *
     * @param alienId the alien id
     * @return the list of sources and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     * @see com.olsh4u.epam.models.Alien
     */
    @Override
    public List<Source> findSourceByAlienId(final String alienId) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_SOURCES_BY_ALIEN_ID,
                ResultSetHandlerFactory.getListResultSetHandler(SOURCES_RESULT_SET_HANDLER), alienId);
    }

    private static final String FIND_SOURCE_BY_ID = "SELECT id, title FROM sources WHERE id = ?";

    /**
     * Find source by id.
     *
     * @param id the source id
     * @return the ${@link Source} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Source findById(final String id) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_SOURCE_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(SOURCES_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_SOURCE_BY_ID = "DELETE FROM sources WHERE id = ?";

    /**
     * Delete source by id.
     *
     * @param id the source id
     * @return true if source was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), DELETE_SOURCE_BY_ID, id);
    }

    private static final String CREATE_SOURCE_BY_ID = "INSERT INTO source VALUES (DEFAULT, ?)";

    /**
     * Create source.
     *
     * @param entity the source
     * @return true if source was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Source entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), CREATE_SOURCE_BY_ID, entity.getTitle());
    }

    private static final String UPDATE_SOURCE_BY_ID = "UPDATE source SET title = ? WHERE id = ?";

    /**
     * Update source.
     *
     * @param entity the source
     * @return true if source was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Source entity) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), UPDATE_SOURCE_BY_ID,
                entity.getTitle(), String.valueOf(entity.getId()));
    }
}
