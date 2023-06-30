package com.olsh4u.epam.models;

import java.util.Objects;

/**
 * The Ability class represents an ability entity, extends {@link BaseEntity} class.
 * It contains properties and methods related to ability information.
 */
public class Ability extends BaseEntity {
    private static final long serialVersionUID = -5599644480354135522L;
    private String title;

    /**
     * Constructs an empty instance of the Ability class.
     */
    public Ability() {
    }

    /**
     * Constructs an instance of the Ability class with the specified ID and name.
     *
     * @param id   The ID of the ability.
     * @param title The name of the ability.
     */
    public Ability(int id, String title) {
        super(id);
        this.title = title;
    }

    /**
     * Constructs an instance of the Ability class with the specified ID.
     *
     * @param id The ID of the ability.
     */
    public Ability(int id) {
        super(id);
    }

    /**
     * Gets the name of the ability.
     *
     * @return The name of the ability.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the name of the ability.
     *
     * @param title The name of the ability.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if two objects of the Ability class are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ability ability = (Ability) o;
        return Objects.equals(title, ability.title);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the Ability object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    /**
     * Returns a string representation of the Ability object.
     *
     * @return A string representation of the Ability object.
     */
    @Override
    public String toString() {
        return "Ability{" +
                "id='" + id + '\'' +
                ", title=" + title +
                '}';
    }
}
