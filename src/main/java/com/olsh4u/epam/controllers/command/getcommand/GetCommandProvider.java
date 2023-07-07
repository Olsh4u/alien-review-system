package com.olsh4u.epam.controllers.command.getcommand;

/**
 * Command provider for GET request
 */
public final class GetCommandProvider {

    private static final GetCommandProvider INSTANCE = new GetCommandProvider();

    /**
     * Map that contains {@link CommandName} and its corresponding implementation of {@link Command} interface.
     */
    private GetCommandProvider() {
    }
}
