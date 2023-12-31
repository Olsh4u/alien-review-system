package com.olsh4u.epam.controllers.command.postcommand.impl;



import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.utils.CommandUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_ERROR;
import static com.olsh4u.epam.utils.Constants.ROUTING_ERROR_JSP;


/**
 * Command for requests that the application cannot process.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.postcommand.PostCommandProvider
 */
public class WrongRequestPostCommand implements Command {

    /**
     * Command for requests that the application cannot process.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferSingleAttribute(ATTRIBUTE_ERROR, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }
}
