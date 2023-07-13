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
 * Command for the most liked serial.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class MostLikedSerialGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the most liked serial.
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
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAllAliens = ServiceFactory.getInstance().getAlienService().countAllAliens();
            List<Alien> alienList = ServiceFactory.getInstance().getAlienService().findMostLikedAliens(page, COUNT_ALIEN_RATING_PAGE);
            List<Source> sourceList = ServiceFactory.getInstance().getSourceService().findAll();
            List<Ability> abilityList = ServiceFactory.getInstance().getAbilityService().findAll();
            List<Planet> planetList = ServiceFactory.getInstance().getPlanetService().findAll();
            List<Alien> last = ServiceFactory.getInstance().getAlienService().latestAlien(COUNT_LATEST_ALIENS);
            req.setAttribute(ATTRIBUTE_ALIENS, alienList);
            req.setAttribute(PARAMETER_COUNT_ALL_ALIENS, countAllAliens);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_ALIEN_RATING_PAGE);
            req.setAttribute(PARAMETER_LAST_ALIENS, last);
            req.setAttribute(PARAMETER_SOURCES, sourceList);
            req.setAttribute(PARAMETER_ABILITY, abilityList);
            req.setAttribute(PARAMETER_PLANET, planetList);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_RATING_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
