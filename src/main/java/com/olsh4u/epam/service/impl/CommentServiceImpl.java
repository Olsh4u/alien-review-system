package com.olsh4u.epam.service.impl;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Comment;
import com.olsh4u.epam.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> findAllCommentForAlien(String alienId) throws ServiceException {
        return null;
    }

    @Override
    public Comment findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean save(Comment comment) throws ServiceException {
        return false;
    }
}
