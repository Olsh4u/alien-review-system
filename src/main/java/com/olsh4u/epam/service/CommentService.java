package com.olsh4u.epam.service;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Comment;

import java.util.List;

/**
 * The service interface for {@link Comment}, all method throws {@link ServiceException}.
 */
public interface CommentService {
    /**
     * Find all comment for alien.
     *
     * @param alienId the alien id
     * @return the list of ${@link Comment}
     * @throws ServiceException if the method failed
     */
    List<Comment> findAllCommentForAlien(String alienId) throws ServiceException;

    /**
     * Find by id comment.
     *
     * @param id the id
     * @return the comment
     * @throws ServiceException if the method failed
     */
    Comment findById(String id) throws ServiceException;

    /**
     * Delete comment by id.
     *
     * @param id the id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save comment.
     *
     * @param comment the comment
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Comment comment) throws ServiceException;
}
