package com.olsh4u.epam.dao;

import  com.olsh4u.epam.exception.DaoException;
import com.olsh4u.epam.models.Comment;

import java.util.List;

/**
 * Dao interface for {@link Comment}, all method throws {@link DaoException}.
 */
public interface CommentDao extends AbstractDao<String, Comment> {

    /**
     * Find all comment for alien.
     *
     * @param alienId the alien id
     * @return the list of ${@link Comment}
     * @throws DaoException if the method failed
     */
    List<Comment> findAllCommentForAlien(String alienId) throws DaoException;

}
