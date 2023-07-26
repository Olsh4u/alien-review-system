package com.olsh4u.epam.controllers.command.postcommand.impl;


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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command to delete {@link com.olsh4u.epam.models.Alien}.
 *
 * @see Command
 * @see  com.olsh4u.epam.controllers.command.postcommand.PostCommandProvider
 */
public class DeleteAlienPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to delete {@link com.olsh4u.epam.models.Alien}. Pictures are also deleted.
     * A folder for files declared in context.
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
        try {
            Alien alien = ServiceFactory.getInstance().getAlienService().findById(id);
            ServiceFactory.getInstance().getAlienService().delete(id);
            File logo = new File(req.getServletContext().getRealPath("") + "img" + File.separator
                    + alien.getLogo());
            File fullLogo = new File(req.getServletContext().getRealPath("") + "img" + File.separator
                    + alien.getFullSizeLogo());
            if (logo.exists() && !logo.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(logo.toPath());
            }
            if (fullLogo.exists() && !fullLogo.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(fullLogo.toPath());
            }


            return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }

    }
}
