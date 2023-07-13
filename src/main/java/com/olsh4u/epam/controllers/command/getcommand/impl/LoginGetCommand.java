package com.olsh4u.epam.controllers.command.getcommand.impl;



import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.utils.CommandUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_INVALID_LOGIN;
import static com.olsh4u.epam.utils.Constants.ROUTING_SIGN_UP_JSP;


/**
 * Command for login for a application.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class LoginGetCommand implements Command {

    /**
     * Command for login for a application.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferSingleAttribute(ATTRIBUTE_INVALID_LOGIN, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_SIGN_UP_JSP, req, resp);
    }
}
