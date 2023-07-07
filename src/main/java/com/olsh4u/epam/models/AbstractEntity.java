package com.olsh4u.epam.models;

import java.io.Serializable;
import java.util.Objects;


/**
 * The BaseEntity class is a base class that implements {@link Serializable} interface.
 * This class represents a basic entity with an identifier.
 */
public class AbstractEntity implements Serializable {

    private final static long serialVersionUID = -7967871648368085337L;

    /**
     * Entity id
     */
    protected int id;

    /**
     * Default constructor for the BaseEntity class.
     */
    public AbstractEntity() {
    }

    /**
     * Constructor for the BaseEntity class with params.
     *
     * @param id the entity's id.
     */
    public AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Gets the identifier of the entity.
     *
     * @return The identifier of the entity.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id The identifier of the entity.
     */
    public void setId(int id) {
        this.id = id;
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
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
