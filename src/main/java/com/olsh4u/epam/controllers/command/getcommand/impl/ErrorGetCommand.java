package com.olsh4u.epam.controllers.command.getcommand.impl;


import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandResponse;
import com.olsh4u.epam.controllers.command.RoutingType;
import com.olsh4u.epam.utils.CommandUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_STATUS_CODE;
import static com.olsh4u.epam.utils.Constants.ROUTING_ERROR_JSP;

public class ErrorGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtils.transferSingleAttribute(ATTRIBUTE_STATUS_CODE, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }
}
