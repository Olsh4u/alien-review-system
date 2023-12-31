package com.olsh4u.epam.controllers.command.postcommand.impl;


import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.AccessRightEnum;
import com.olsh4u.epam.models.Comment;
import com.olsh4u.epam.models.User;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command to delete a {@link Comment}.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.postcommand.PostCommandProvider
 */
public class DeleteCommentPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to delete a {@link Comment}. It also checks if the {@link User} has rights to delete.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_ID);
        HttpSession session = req.getSession();
        try {
            User user = ServiceFactory.getInstance().getUserService()
                    .findByLogin(session.getAttribute(ATTRIBUTE_LOGIN).toString());
            Comment comment = ServiceFactory.getInstance().getCommentService().findById(id);
            if (user != null && comment != null && (comment.getUser().getId() == user.getId() || user.getAccessRights() == AccessRightEnum.ADMIN.ordinal())) {
                ServiceFactory.getInstance().getCommentService().delete(id);
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
