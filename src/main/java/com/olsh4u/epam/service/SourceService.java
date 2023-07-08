package com.olsh4u.epam.service;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Source;

import java.util.List;
/**
 * The service interface for {@link com.olsh4u.epam.models.Source}, all method throws {@link ServiceException}.
 */
public interface SourceService {
    /**
     * Find all sources.
     *
     * @return the list of sources
     * @throws ServiceException if the method failed
     */
    List<Source> findAll() throws ServiceException;

    /**
     * Find source page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of source
     * @throws ServiceException if the method failed
     */
    List<Source> findSourcesPageByPage(int page, int limit) throws ServiceException;

    /**
     * Count all sources.
     *
     * @return number of all sources
     * @throws ServiceException if the method failed
     */
    int countAllSources() throws ServiceException;

    /**
     * Find source by title.
     *
     * @param title the source title
     * @return the source
     * @throws ServiceException if the method failed
     */
    Source findByTitle(String title) throws ServiceException;

    /**
     * Delete source.
     *
     * @param id the source id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save source.
     *
     * @param source the source
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Source source) throws ServiceException;
}
