package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command for dislike serial.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class DislikeAlienGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);


    /**
     * Dislike alien.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String alienId = req.getParameter(PARAMETER_ID);
        int userId = (Integer) req.getSession().getAttribute(ATTRIBUTE_USER_ID);

        try {
            ServiceFactory.getInstance().getAlienService().dislikeAlien(String.valueOf(userId), alienId);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id" + alienId, req, resp);
        } catch (ServiceException e){
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
