package com.olsh4u.epam.controllers.command.getcommand.impl;



import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.*;


/**
 * Command for logout for a application.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class LogOutGetCommand implements Command {

    /**
     * Command for logout for a application.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(ATTRIBUTE_LOGIN);
        session.removeAttribute(ATTRIBUTE_ACCESS_RIGHTS);
        session.removeAttribute(ATTRIBUTE_USER_ID);
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
    }
}
