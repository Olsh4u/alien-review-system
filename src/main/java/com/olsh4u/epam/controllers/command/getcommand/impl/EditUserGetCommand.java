package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.User;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.CommandUtils;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.*;

/**
 * Command for the {@link com.olsh4u.epam.models.User} edit page.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class EditUserGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the {@link com.olsh4u.epam.models.User} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferMapAttribute(ATTRIBUTE_INVALID_LOGIN, req);
        String login = req.getParameter(PARAMETER_LOGIN);
        if (login != null) {
            try {
                User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
                req.setAttribute(ATTRIBUTE_USER, user);
            } catch (ServiceException e) {
                logger.error(e);
                return RoutingUtils.routingErrorPage(req, resp, e.getCode());
            }
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_USER_JSP, req, resp);
    }
}
