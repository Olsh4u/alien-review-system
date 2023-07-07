package com.olsh4u.epam.dao;

import com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Source;

import java.util.List;

/**
 * Dao interface for {@link Source}, all method throws {@link DaoException}.
 */
public interface SourceDao extends AbstractDao<String, Source> {
    /**
     * Find all sources.
     *
     * @return the list of sources
     * @throws DaoException if the method failed
     */
    List<Source> findAll() throws DaoException;

    /**
     * Find sources page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of sources
     * @throws DaoException if the method failed
     */
    List<Source> findSourcesPageByPage(int page, int limit) throws DaoException;

    /**
     * Count of all sources.
     *
     * @return number of all sources
     * @throws DaoException if the method failed
     */
    int countAllSources() throws DaoException;

    /**
     * Find source by title.
     *
     * @param title the source title
     * @return the source
     * @throws DaoException if the method failed
     */
    Source findByTitle(String title) throws DaoException;

    /**
     * Find sources by alien id.
     *
     * @param alienId the alien id
     * @return the list of sources
     * @throws DaoException if the method failed
     * @see com.olsh4u.epam.models.Alien;
     */
    List<Source> findSourceByAlienId(String alienId) throws DaoException;
}
