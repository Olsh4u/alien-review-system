package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command for liked serial.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class LikedAlienGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for liked serial.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getSession().getAttribute(ATTRIBUTE_USER_ID).toString();
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAlien = ServiceFactory.getInstance().getAlienService().countAllAliensThatUserLiked(userId);
            List<Alien> alienList = ServiceFactory.getInstance()
                    .getAlienService().findAliensThatUserLiked(userId, page, COUNT_ALIENS_IN_LIKED_PAGE);
            req.setAttribute(ATTRIBUTE_ALIENS, alienList);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_ALIENS_IN_LIKED_PAGE);
            req.setAttribute(PARAMETER_COUNT_ALL_ALIENS, countAlien);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_LIKED_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
