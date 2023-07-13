package com.olsh4u.epam.controllers.command.getcommand.impl;


import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.*;

/**
 * Command for start watch serial.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class WatchSerialGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for start watch serial.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String serialId = req.getParameter(PARAMETER_ID);
        int userId = (Integer) req.getSession().getAttribute(ATTRIBUTE_USER_ID);
        try {
            ServiceFactory.getInstance().getAlienService().toWatchAlien(String.valueOf(userId), serialId);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
