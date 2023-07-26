package com.olsh4u.epam.controllers.command.postcommand;

import com.olsh4u.epam.controllers.command.Command;
import com.olsh4u.epam.controllers.command.CommandName;
import com.olsh4u.epam.controllers.command.postcommand.impl.*;
import com.olsh4u.epam.controllers.command.postcommand.testimpl.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Command provider for POST request.
 */
public final class PostCommandProvider {

    private static final PostCommandProvider INSTANCE = new PostCommandProvider();
    /**
     * Map that contains {@link CommandName} and its corresponding implementation of {@link Command} interface.
     */
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private PostCommandProvider() {
        repository.put(CommandName.REGISTRATION, new RegistrationPostCommand());
        repository.put(CommandName.LOGIN, new LoginPostCommand());
        repository.put(CommandName.SAVE_SOURCE, new SaveSourcePostCommand());
        repository.put(CommandName.DELETE_SOURCE, new DeleteSourcePostCommand());
        repository.put(CommandName.SAVE_ALIEN, new SaveAlienPostCommand());
        repository.put(CommandName.ADD_USER, new AddUserPostCommand());
        repository.put(CommandName.SAVE_ABILITY, new SaveAbilityPostCommand());
        repository.put(CommandName.SAVE_PLANET, new SaveStudioPostCommand());
        repository.put(CommandName.CHANGE_PASSWORD, new ChangePasswordPostCommand());
        repository.put(CommandName.CHANGE_ACCESS_RIGHTS, new ChangeUserRolePostCommand());
        repository.put(CommandName.SAVE_COMMENT, new SaveCommentPostCommand());
        repository.put(CommandName.DELETE_ABILITY, new DeleteAbilityPostCommand());
        repository.put(CommandName.DELETE_PLANET, new DeletePlanetPostCommand());
        repository.put(CommandName.DELETE_ALIEN, new DeleteAlienPostCommand());
        repository.put(CommandName.DELETE_COMMENT, new DeleteCommentPostCommand());
        repository.put(CommandName.CHANGE_AVATAR, new ChangeUserAvatarPostCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestPostCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PostCommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Method for getting a {@link Command} by its {@link String} representation.
     *
     * @param requestURI the {@link String} representation request URI
     * @return {@link Command} and wrong request {@link Command} if request URI incorrect
     */
    public Command getCommand(final String requestURI) {
        try {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
