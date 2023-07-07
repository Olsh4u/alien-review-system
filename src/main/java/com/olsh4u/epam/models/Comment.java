package com.olsh4u.epam.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Comment class represents an comment entity, extends {@link AbstractEntity} class.
 * It contains properties and methods related to comment information, publication date and associated data.
 */
public class Comment extends AbstractEntity {
    private static final long serialVersionUID = -4451328805432622998L;
    private User user;
    private Alien alien;
    private String comment;
    private LocalDateTime publicationDate;

    /**
     * Constructs an empty instance of the Comment class.
     */
    public Comment() {
    }

    /**
     * Constructs an instance of the Comment class with the specified ID.
     *
     * @param id The ID of the comment.
     */
    public Comment(int id) {
        super(id);
    }

    /**
     * Constructs an instance of the Comment class with the specified parameters.
     *
     * @param id      The ID of the comment.
     * @param user    The user who made the comment.
     * @param alien   The alien entity the comment is related to.
     * @param comment The content of the comment.
     */
    public Comment(int id, User user, Alien alien, String comment) {
        this.id = id;
        this.user = user;
        this.alien = alien;
        this.comment = comment;
    }

    /**
     * Constructs an instance of the Comment class with the specified parameters.
     *
     * @param id              The ID of the comment.
     * @param user            The user who made the comment.
     * @param alien           The alien entity the comment is related to.
     * @param comment         The content of the comment.
     * @param publicationDate The date and time when the comment was published.
     */
    public Comment(int id, User user, Alien alien, String comment, LocalDateTime publicationDate) {
        super(id);
        this.user = user;
        this.alien = alien;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }

    /**
     * Gets the user who made the comment.
     *
     * @return The user who made the comment.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the comment.
     *
     * @param user The user who made the comment.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the alien entity the comment is related to.
     *
     * @return The alien entity the comment is related to.
     */
    public Alien getAlien() {
        return alien;
    }

    /**
     * Sets the alien entity the comment is related to.
     *
     * @param alien The alien entity the comment is related to.
     */
    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    /**
     * Gets the content of the comment.
     *
     * @return The content of the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the content of the comment.
     *
     * @param comment The content of the comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the date and time when the comment was published.
     *
     * @return The date and time when the comment was published.
     */
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets the date and time when the comment was published.
     *
     * @param publicationDate The date and time when the comment was published.
     */
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Checks if two objects of the Comment class are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(user, comment1.user) &&
                Objects.equals(alien, comment1.alien) &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(publicationDate, comment1.publicationDate);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the Comment object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, alien, comment, publicationDate);
    }

    /**
     * Returns a string representation of the Comment object.
     *
     * @return A string representation of the Comment object.
     */
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", alien=" + alien +
                ", comment='" + comment + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
