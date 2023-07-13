package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Planet;
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
 * Command for the {@link Planet} edit page.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class EditPlanetGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the {@link Planet} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferMapAttribute(ATTRIBUTE_PLANET_PROBLEM, req);
        try {
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAllPlanets = ServiceFactory.getInstance().getPlanetService().countAllPlanets();
            List<Planet> planetList = ServiceFactory.getInstance()
                    .getPlanetService().findPlanetsPageByPage(page, COUNT_PLANETS_IN_ADMIN_PAGE);
            req.setAttribute(PARAMETER_PLANET, planetList);
            req.setAttribute(PARAMETER_COUNT_ALL_PLANETS, countAllPlanets);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_PLANETS_IN_ADMIN_PAGE);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_PLANET_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
