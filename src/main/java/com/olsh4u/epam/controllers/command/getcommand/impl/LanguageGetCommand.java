package com.olsh4u.epam.controllers.command.getcommand.impl;



import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.HEADER_REFERER;
import static com.olsh4u.epam.utils.Constants.PARAMETER_LANGUAGE;


/**
 * Command for the change locale of the application.
 *
 * @see Command
 * @see com.olsh4u.epam.controllers.command.getcommand.GetCommandProvider
 */
public class LanguageGetCommand implements Command {

    /**
     * Command for the change locale of the application.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(PARAMETER_LANGUAGE);
        Cookie languageCookie = new Cookie(PARAMETER_LANGUAGE, language);
        resp.addCookie(languageCookie);
        return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
    }
}
