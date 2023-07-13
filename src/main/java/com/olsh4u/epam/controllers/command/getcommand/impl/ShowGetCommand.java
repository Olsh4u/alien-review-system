package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.models.Source;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.utils.CommandUtils;
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
 * Command for the serial page.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class ShowGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the serial page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferMapAttribute(ATTRIBUTE_COMMENT_PROBLEM, req);
        CommandUtils.transferMapAttribute(ATTRIBUTE_ALIEN_PROBLEM, req);
        String id = req.getParameter(PARAMETER_ID);
        try {
            Alien alien = ServiceFactory.getInstance().getAlienService().findById(id);
            List<Alien> last = ServiceFactory.getInstance().getAlienService().latestAlien(COUNT_LATEST_ALIENS);
            List<Source> sourceList = ServiceFactory.getInstance().getSourceService().findAll();
            List<Ability> abilityList = ServiceFactory.getInstance().getAbilityService().findAll();
            List<Planet> planetList = ServiceFactory.getInstance().getPlanetService().findAll();

            if (req.getSession().getAttribute(ATTRIBUTE_USER_ID) != null) {
                boolean seenStatus = ServiceFactory.getInstance()
                        .getAlienService()
                        .userSeenThisAlien(String.valueOf(req.getSession().getAttribute(ATTRIBUTE_USER_ID)), id);
                req.setAttribute(ATTRIBUTE_SEEN_STATUS, seenStatus);
                boolean likedStatus = ServiceFactory.getInstance()
                        .getAlienService()
                        .userLikedThisAlien(String.valueOf(req.getSession().getAttribute(ATTRIBUTE_USER_ID)), id);
                req.setAttribute(ATTRIBUTE_LIKED_STATUS, likedStatus);
            }

            req.setAttribute(ATTRIBUTE_SOURCES, sourceList);
            req.setAttribute(ATTRIBUTE_ALIENS, alien);
            req.setAttribute(PARAMETER_LAST_ALIENS, last);
            req.setAttribute(ATTRIBUTE_ABILITY, abilityList);
            req.setAttribute(ATTRIBUTE_PLANET, planetList);

            return new CommandResponse(RoutingType.FORWARD, ROUTING_SHOW_JSP, req, resp);
        } catch (ServiceException e) {
            logger.debug(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }

    }
}
