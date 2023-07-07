package com.olsh4u.epam.controllers.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class is a return type for {@link Command}.
 */
public class CommandResponse {
    /**
     * Routing type.
     */
    private final RoutingType routingType;
    /**
     * Page title for navigation.
     */
    private final String routingPage;

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /**
     * Constructs an instance of the command response class with parameters.
     *
     * @param routingType the routing type
     * @param routingPage the routing page
     * @param request     the HttpServletRequest
     * @param response    the HttpServletResponse
     */
    public CommandResponse(RoutingType routingType, String routingPage,
                           HttpServletRequest request, HttpServletResponse response) {
        this.routingType = routingType;
        this.routingPage = routingPage;
        this.request = request;
        this.response = response;
    }

    /**
     * Gets routing type.
     *
     * @return the routing type
     */
    public RoutingType getRoutingType() {
        return routingType;
    }

    /**
     * Gets routing page.
     *
     * @return the routing page
     */
    public String getRoutingPage() {
        return routingPage;
    }

    /**
     * Gets request.
     *
     * @return the HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Gets response.
     *
     * @return the HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return response;
    }
}
