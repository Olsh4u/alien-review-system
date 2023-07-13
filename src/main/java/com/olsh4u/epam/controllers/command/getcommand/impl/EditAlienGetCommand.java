package com.olsh4u.epam.controllers.command.getcommand.impl;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;
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
 * Command for the {@link com.olsh4u.epam.models.Alien} edit page.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class EditAlienGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the {@link com.olsh4u.epam.models.Alien} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferMapAttribute(ATTRIBUTE_ALIEN_PROBLEM, req);
        try {
            List<Ability> abilityList = ServiceFactory.getInstance().getAbilityService().findAll();
            List<Planet> planetList = ServiceFactory.getInstance().getPlanetService().findAll();
            List<Source> sourceList = ServiceFactory.getInstance().getSourceService().findAll();
            req.setAttribute(PARAMETER_SOURCES, sourceList);
            req.setAttribute(PARAMETER_PLANET, planetList);
            req.setAttribute(PARAMETER_ABILITY, abilityList);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_ALIEN_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}