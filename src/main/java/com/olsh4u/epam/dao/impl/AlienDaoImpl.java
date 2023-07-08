package com.olsh4u.epam.dao.impl;

import com.olsh4u.epam.dao.AlienDao;
import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandler;
import com.olsh4u.epam.dao.impl.handler.ResultSetHandlerFactory;
import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.models.Source;
import com.olsh4u.epam.models.form.SearchForm;
import com.olsh4u.epam.models.form.SearchQuery;
import com.olsh4u.epam.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link AlienDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Alien}.
 *
 * @see Transaction
 * @see DaoException
 */
public class AlienDaoImpl implements AlienDao {
    private static final String SELECT_ALL_ALIENS_FIELD = "SELECT s.id, s.name, s.description, s.logo, s.full_size_logo," +
            " s.first_appearance, s.likes_count, s.ability_id, s.planet_id FROM aliens s";
    private static final String FIND_ALIEN_BY_NAME = SELECT_ALL_ALIENS_FIELD + " WHERE s.name = ?";

    private static final String FIND_ALL_ALIENS = SELECT_ALL_ALIENS_FIELD;

    private static final String FIND_ALIENS_PAGE_BY_PAGE = SELECT_ALL_ALIENS_FIELD + " LIMIT ? OFFSET ?";

    private static final String FIND_MOST_LIKED_ALIENS = SELECT_ALL_ALIENS_FIELD + " ORDER BY s.likes_count DESC LIMIT ? OFFSET ?";

    private static final String LATEST_ALIENS = SELECT_ALL_ALIENS_FIELD + " ORDER BY s.id DESC LIMIT ?";

    private static final String COUNT_ALL_ALIENS = "SELECT COUNT(*) FROM aliens";

    private static final String FIND_ALIEN_BY_SEARCH_FORM_2 = "SELECT DISTINCT s.id, s.* FROM aliens s LEFT JOIN aliens_sources sg ON s.id = sg.alien_id WHERE (name like ? or description like ?)";

    private static final String COUNT_ALL_ALIENS_BY_SEARCH_FORM = "SELECT COUNT(DISTINCT s.id) FROM aliens s LEFT JOIN aliens_sources sg ON s.id = sg.alien_id WHERE (name like ? or description like ?)";

    private static final String FIND_ALIEN_BY_ID = SELECT_ALL_ALIENS_FIELD + " WHERE s.id = ?";

    private static final String DELETE_ALIEN_BY_ID = "DELETE FROM aliens WHERE id = ?";

    private static final String CREATE_ALIEN = "INSERT INTO aliens VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";
    private static final String ALIEN_SOURCES_VALUES = "INSERT INTO aliens_sources VALUES (?, ?)";
    private static final String UPDATE_ALIEN = "UPDATE aliens SET name = ?, description = ?, logo = ?, full_logo = ?," +
            " first_appearance = ?, ability_id = ?, planet_id = ? WHERE id = ?";
    private static final String DELETE_ALIENS_SOURCES = "DELETE FROM aliens_sources WHERE alien_id = ?";
    private static final String WATCH_ALIEN = "INSERT INTO viewed VALUES (?, ?)";

    private static final String STOP_WATCH_ALIEN = "DELETE FROM viewed WHERE (user_id = ? and alien_id = ?)";

    private static final String ALIEN_IS_WATCH_STATUS = SELECT_ALL_ALIENS_FIELD + " JOIN viewed v on s.id = v.alien_id WHERE v.user_id = ? and v.alien_id = ?";

    private static final String FIND_ALIENS_THAT_I_WATCH = SELECT_ALL_ALIENS_FIELD + " JOIN viewed v on s.id = v.alien_id WHERE v.user_id = ? LIMIT ? OFFSET ?";

    private static final String COUNT_ALL_ALIENS_THAT_I_WATCH = "SELECT COUNT(*) FROM viewed WHERE user_id = ?";
    private static final String FIND_ALIENS_THAT_I_LIKED = SELECT_ALL_ALIENS_FIELD + " JOIN liked l on s.id = l.alien_id WHERE l.user_id = ? LIMIT ? OFFSET ?";
    private static final String COUNT_ALL_ALIENS_THAT_I_LIKED = "SELECT COUNT(*) FROM liked WHERE user_id = ?";
    private static final String LIKE_ALIEN = "UPDATE alien SET likes_count = likes_count + 1 WHERE id = ?";
    private static final String ADD_IN_LIKED = "INSERT INTO liked VALUES (?, ?)";
    private static final String DISLIKE_ALIEN = "UPDATE alien SET likes_count = likes_count - 1 WHERE id = ?";
    private static final String REMOVE_IN_LIKED = "DELETE FROM liked WHERE (user_id = ? and alien_id = ?)";
    private static final String ALIEN_IS_LIKED_STATUS = SELECT_ALL_ALIENS_FIELD + " JOIN liked l on s.id = l.alien_id WHERE l.user_id = ? and l.alien_id = ?";

    /**
     * An object that provides access to a data source.
     */
    private final Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Alien} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Alien> ALIENS_RESULT_SET_HANDLER = new ResultSetHandler<>() {
        @Override
        public Alien handle(final ResultSet rs) throws DaoException {
            Alien alien= new Alien();
            try {
                alien.setId(rs.getInt("id"));
                alien.setName(rs.getString("name"));
                alien.setDescription(rs.getString("description"));
                alien.setLogo(rs.getString("logo"));
                alien.setFullSizeLogo(rs.getString("full_logo"));
                alien.setFirstAppearance(rs.getInt("release_date"));
                alien.setLikesCount(rs.getInt("count_like"));
                Ability ability = new Ability();
                ability.setId(rs.getInt("ability_id"));
                alien.setAbility(ability);
                Planet planet = new Planet();
                planet.setId(rs.getInt("planet_id"));
                alien.setPlanet(planet);
                alien.setSourceList(new ArrayList<>());
                alien.setCommentList(new ArrayList<>());
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return alien;
        }
    };

    public AlienDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Find alien by aline name.
     *
     * @param name the name
     * @return the {@link Alien} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Alien findAlienByName(final String name) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALIEN_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(ALIENS_RESULT_SET_HANDLER), name);
    }

    @Override
    public List<Alien> findAll() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALL_ALIENS,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER));
    }

    /**
     * Find aliens page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Alien> findAliensPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_ALIENS_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), limit, offset);
    }

    /**
     * Find most liked aliens.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Alien> findMostLikedAlien(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_MOST_LIKED_ALIENS,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), limit, offset);
    }

    /**
     * Latest alien.
     *
     * @param limit the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Alien> latestAlien(final int limit) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), LATEST_ALIENS,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), limit);
    }

    /**
     * Count all aliens.
     *
     * @return number of all aliens
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllAliens() throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_ALIENS,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    /**
     * Find aliens by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    @Override
    public List<Alien> findAlienBySearchForm(final SearchForm searchForm, final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        SearchQuery query = buildSearchQuery(searchForm, FIND_ALIEN_BY_SEARCH_FORM_2);
        query.getSql().append(" LIMIT ? OFFSET ?");
        query.getParams().add(limit);
        query.getParams().add(offset);
        return JdbcUtil.select(transaction.getConnection(), query.getSql().toString(),
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), query.getParams().toArray());
    }

    /**
     * Build SQL query based on searchForm.
     *
     * @param searchForm the search form
     * @param temp       Select filed string
     * @return search query
     * @see SearchForm
     * @see SearchQuery
     */
    private SearchQuery buildSearchQuery(final SearchForm searchForm, final String temp) {
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(temp);
        param.add("%" + searchForm.getQuery() + "%");
        param.add("%" + searchForm.getQuery() + "%");
        JdbcUtil.populateSqlAndParams(sql, param, searchForm.getSources(), "sg.source_id = ?");
        JdbcUtil.populateSqlAndParams(sql, param, searchForm.getAbility(), "s.ability_id = ?");
        JdbcUtil.populateSqlAndParams(sql, param, searchForm.getPlanet(), "s.planet_id = ?");
        return new SearchQuery(sql, param);
    }

    /**
     * Count all alien by search form.
     *
     * @param searchForm the search form
     * @return number of aliens by search form
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllAliensBySearchForm(final SearchForm searchForm) throws DaoException {
        SearchQuery query = buildSearchQuery(searchForm, COUNT_ALL_ALIENS_BY_SEARCH_FORM);
        return JdbcUtil.select(transaction.getConnection(), query.getSql().toString(),
                ResultSetHandlerFactory.getCountResultSetHandler(), query.getParams().toArray());
    }

    /**
     * Find alien by id.
     *
     * @param id the alien id
     * @return the ${@link Alien} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Alien findById(final String id) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), FIND_ALIEN_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(ALIENS_RESULT_SET_HANDLER), id);
    }

    /**
     * Delete aliens by id.
     *
     * @param id the aliens id
     * @return true if aliens was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), DELETE_ALIEN_BY_ID, id);
    }

    /**
     * Create alien. Else add sources value in table aliens_sources.
     *
     * @param alien the alien
     * @return true if alien was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Alien alien) throws DaoException {
        int alienId = JdbcUtil.executeAndReturnIndex(transaction.getConnection(), CREATE_ALIEN,
                alien.getName(), alien.getDescription(), alien.getLogo(), alien.getFullSizeLogo(), alien.getFirstAppearance(),
                alien.getAbility().getId(), alien.getPlanet().getId());
        JdbcUtil.executeBatch(transaction.getConnection(), ALIEN_SOURCES_VALUES,
                refactorToParamList(alienId, alien.getSourceList()));
        return alienId != 0;
    }

    private List<Object[]> refactorToParamList(final int alienId, final List<Source> sources) {
        List<Object[]> paramList = new ArrayList<>();
        for (Source s : sources
        ) {
            paramList.add(new Object[]{alienId, s.getId()});
        }
        return paramList;
    }

    /**
     * Update alien. Delete all value which correspond this alien in the table aliens_sources and add new value.
     *
     * @param entity the alien
     * @return true if alien was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Alien entity) throws DaoException {
        JdbcUtil.execute(transaction.getConnection(), DELETE_ALIENS_SOURCES, entity.getId());
        JdbcUtil.executeBatch(transaction.getConnection(), ALIEN_SOURCES_VALUES,
                refactorToParamList(entity.getId(), entity.getSourceList()));
        return JdbcUtil.execute(transaction.getConnection(), UPDATE_ALIEN,
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullSizeLogo(),
                entity.getFirstAppearance(), entity.getAbility().getId(), entity.getPlanet().getId(), entity.getId());
    }

    /**
     * To watch alien. Add user id and alien id in the table viewed.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean toWatchAlien(final String userId, final String alienId) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), WATCH_ALIEN, userId, alienId);
    }

    /**
     * Stop watch alien. Remove user id and alien id in the table viewed.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean stopWatchAlien(final String userId, final String alienId) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), STOP_WATCH_ALIEN, userId, alienId);
    }

    /**
     * Check if the user is watching this alien.
     *
     * @param alienId the alien id
     * @param userId   the user id
     * @return true if user watch this alien and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean userWatchThisAlien(final String alienId, final String userId) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), ALIEN_IS_WATCH_STATUS,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId, alienId) != 0;
    }

    /**
     * Find all aliens that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Alien> findAliensThatUserWatch(final String userId, final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_ALIENS_THAT_I_WATCH,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), userId, limit, offset);
    }

    /**
     * Count all aliens that user watch.
     *
     * @param userId the user id
     * @return number of aliens that is watch user
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllAliensThatUserWatch(final String userId) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_ALIENS_THAT_I_WATCH,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId);
    }

    /**
     * Find aliens that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of aliens and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Alien> findAliensThatUserLiked(final String userId, final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JdbcUtil.select(transaction.getConnection(), FIND_ALIENS_THAT_I_LIKED,
                ResultSetHandlerFactory.getListResultSetHandler(ALIENS_RESULT_SET_HANDLER), userId, limit, offset);
    }

    /**
     * Count all aliens that user liked.
     *
     * @param userId the user id
     * @return number of aliens that liked user
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllAliensThatUserLiked(final String userId) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), COUNT_ALL_ALIENS_THAT_I_LIKED,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId);
    }

    /**
     * Add an alien to user liked table. And increase value like in the table aliens.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean likeAlien(final String userId, final String alienId) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), LIKE_ALIEN, alienId) &&
                JdbcUtil.execute(transaction.getConnection(), ADD_IN_LIKED, userId, alienId);
    }

    /**
     * Remove an alien to user liked. And reduce value like in the table alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean dislikeAlien(final String userId, final String alienId) throws DaoException {
        return JdbcUtil.execute(transaction.getConnection(), DISLIKE_ALIEN, alienId) &&
                JdbcUtil.execute(transaction.getConnection(), REMOVE_IN_LIKED, userId, alienId);
    }

    /**
     * Check whether the user likes this alien.
     *
     * @param userId   the user id
     * @param alienId the alien id
     * @return true if user liked this alien and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean userLikedThisAlien(final String userId, final String alienId) throws DaoException {
        return JdbcUtil.select(transaction.getConnection(), ALIEN_IS_LIKED_STATUS,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId, alienId) != 0;
    }
}
