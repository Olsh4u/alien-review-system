package com.olsh4u.epam.controllers.command.postcommand.impl;


import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Alien;
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
 * Command to save {@link Comment}.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.postcommand.PostCommandProvider
 */
public class SaveCommentPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to save {@link Comment}. Handles both change and creation requests.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String alienId = req.getParameter(PARAMETER_ID);
        String commentId = req.getParameter(PARAMETER_COMMENT_ID) != null ? req.getParameter(PARAMETER_COMMENT_ID) : String.valueOf(0);
        String text = req.getParameter(PARAMETER_COMMENT);
        try {
            User user = ServiceFactory.getInstance().getUserService()
                    .findByLogin(session.getAttribute(ATTRIBUTE_LOGIN).toString());
            Alien alien = ServiceFactory.getInstance().getAlienService().findById(alienId);
            if (alien != null) {
                Comment comment = new Comment(Integer.parseInt(commentId), user, alien, text);
                ServiceFactory.getInstance().getCommentService().save(comment);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + alienId, req, resp);
            } else {
                return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (ServiceException e) {
            if (e.getCode() == ServiceException.BAD_REQUEST && e.getErrors() != null) {
                req.getSession().setAttribute(ATTRIBUTE_COMMENT_PROBLEM, e.getErrors());
                return new CommandResponse(RoutingType.REDIRECT,
                        ROUTING_SHOW_PAGE + "?id=" + alienId, req, resp);
            }
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            logger.error("Cannot cast value to int - " + commentId, e);
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
