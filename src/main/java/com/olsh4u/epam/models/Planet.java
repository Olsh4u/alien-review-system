package com.olsh4u.epam.models;

import java.util.Objects;

/**
 * The Planet class represents a planet entity, extends {@link AbstractEntity} class.
 * It contains properties and methods related to planet information.
 */
public class Planet extends AbstractEntity {
    private static final long serialVersionUID = -5599644480354135522L;
    private String title;

    /**
     * Constructs an empty instance of the Planet class.
     */
    public Planet() {
    }

    /**
     * Constructs an instance of the Planet class with the specified ID and title.
     *
     * @param id   The ID of the planet.
     * @param title The title of the planet.
     */
    public Planet(int id, String title) {
        super(id);
        this.title = title;
    }

    /**
     * Constructs an instance of the Planet class with the specified ID.
     *
     * @param id The ID of the planet.
     */
    public Planet(int id) {
        super(id);
    }

    /**
     * Gets the title of the planet.
     *
     * @return The title of the planet.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the planet.
     *
     * @param title The title of the planet.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if two objects of the Planet class are equal.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Planet planet = (Planet) o;
        return Objects.equals(title, planet.title);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the Planet object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    /**
     * Returns a string representation of the Planet object.
     *
     * @return A string representation of the Planet object.
     */
    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", title=" + title +
                '}';
    }
}