package com.olsh4u.epam.models;

import java.util.Objects;

/**
 * The Source class represents a source entity, extends {@link BaseEntity} class.
 * It contains properties and methods related to source information.
 */
public class Source extends BaseEntity {
    private static final long serialVersionUID = -5599644480354135522L;
    private String title;

    /**
     * Constructs an empty instance of the Source class.
     */
    public Source() {
    }

    /**
     * Constructs an instance of the Source class with the specified ID and name.
     *
     * @param id   The ID of the source.
     * @param title The title of the source.
     */
    public Source(int id, String title) {
        super(id);
        this.title = title;
    }

    /**
     * Constructs an instance of the Source class with the specified ID.
     *
     * @param id The ID of the source.
     */
    public Source(int id) {
        super(id);
    }

    /**
     * Gets the name of the source.
     *
     * @return The name of the source.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the name of the title.
     *
     * @param title The name of the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if two objects of the Source class are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Source source = (Source) o;
        return Objects.equals(title, source.title);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the Source object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    /**
     * Returns a string representation of the Source object.
     *
     * @return A string representation of the Source object.
     */
    @Override
    public String toString() {
        return "Ability{" +
                "id='" + id + '\'' +
                ", title=" + title +
                '}';
    }
}
