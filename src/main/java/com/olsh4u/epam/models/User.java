package com.olsh4u.epam.models;

import java.util.Objects;

/**
 * The User class represents a user entity, extends {@link AbstractEntity} class.
 * It contains properties and methods related to user information and access rights.
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = -3150289886212330216L;

    private String login;
    /**
     * Password represent a hash using Bcrypt cipher.
     *
     */
    private String password;
    /**
     * Avatar represents the name of the image with the extension.
     */
    private String avatar;
    /**
     * Text description roleId represent {@link AccessRightEnum} enum.
     */
    private int accessRights;

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Constructor for the User class with an ID parameter.
     *
     * @param id The identifier of the user.
     */
    public User(int id) {
        super(id);
    }

    /**
     * Constructor for the User class with ID, login, password, and access rights parameters.
     *
     * @param id           The identifier of the user.
     * @param login        The login name of the user.
     * @param password     The password of the user.
     * @param accessRights The access rights of the user.
     */
    public User(int id, String login, String password, int accessRights) {
        super(id);
        this.login = login;
        this.password = password;
        this.accessRights = accessRights;
    }

    /**
     * Constructor for the User class with login, password, avatar, and access rights parameters.
     *
     * @param login        The login name of the user.
     * @param password     The password of the user.
     * @param avatar       The avatar image URL of the user.
     * @param accessRights The access rights of the user.
     */
    public User(String login, String password, String avatar, int accessRights) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.accessRights = accessRights;
    }

    /**
     * Constructor for the User class with ID, login, password, avatar, and access rights parameters.
     *
     * @param id           The identifier of the user.
     * @param login        The login name of the user.
     * @param password     The password of the user.
     * @param avatar       The avatar image URL of the user.
     * @param accessRights The access rights of the user.
     */
    public User(int id, String login, String password, String avatar, int accessRights) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.accessRights = accessRights;
    }

    /**
     * Gets the login name of the user.
     *
     * @return The login name of the user.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login name of the user.
     *
     * @param login The login name of the user.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the avatar image URL of the user.
     *
     * @return The avatar image URL of the user.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar image URL of the user.
     *
     * @param avatar The avatar image URL of the user.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets the access rights of the user.
     *
     * @return The access rights of the user.
     */
    public int getAccessRights() {
        return accessRights;
    }

    /**
     * Sets the access rights of the user.
     *
     * @param accessRights The access rights of the user.
     */
    public void setAccessRights(int accessRights) {
        this.accessRights = accessRights;
    }

    /**
     * Checks if two objects are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return accessRights == user.accessRights &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(avatar, user.avatar);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, avatar, accessRights);
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", accessRights=" + accessRights +
                ", id=" + id +
                '}';
    }
}

