package com.olsh4u.epam.service.factory;

import com.olsh4u.epam.service.*;
import com.olsh4u.epam.service.impl.*;

/**
 * Class for accessing a specific Service.
 * Represents an implementation of the Singleton and Factory design pattern.
 */
public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private final CommentService commentService = new CommentServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final AbilityService abilityService = new AbilityServiceImpl();
    private final PlanetService planetService = new PlanetServiceImpl();
    private final AlienService alienService = new AlienServiceImpl();
    private final SourceService sourceService = new SourceServiceImpl();
    private final SecurityService securityService = new SecurityServiceImpl();


    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets alien service.
     *
     * @return the alien service
     */
    public AlienService getAlienService() {
        return alienService;
    }

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    public CommentService getCommentService() {
        return commentService;
    }

    /**
     * Gets source service.
     *
     * @return the source service
     */
    public SourceService getSourceService() {
        return sourceService;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets ability service.
     *
     * @return the ability service
     */
    public AbilityService getAbilityService() {
        return abilityService;
    }

    /**
     * Gets planet service.
     *
     * @return the planet service
     */
    public PlanetService getPlanetService() {
        return planetService;
    }

    /**
     * Gets security service.
     *
     * @return the security service
     */
    public SecurityService getSecurityService() {
        return securityService;
    }
}
