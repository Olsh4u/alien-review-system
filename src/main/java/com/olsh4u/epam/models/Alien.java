package com.olsh4u.epam.models;

import java.util.List;
import java.util.Objects;

/**
 * The Alien class represents an alien entity, extends {@link BaseEntity} class.
 * It contains properties and methods related to alien information, abilities, and associated data.
 */
public class Alien extends BaseEntity {

    private static final long serialVersionUID = -8730462953097921508L;

    private String name;
    private String description;
    /**
     * Logo represents the name of the image with the extension.
     */
    private String logo;
    /**
     * Full size logo represents the name of the full size image with the extension.
     */
    private String fullSizeLogo;
    private int firstAppearance;
    private int likesCount;
    private Planet planet;
    private Ability ability;

    private List<Source> sourceList;
    private List<Comment> commentList;

    /**
     * Default constructor for the Alien class.
     */
    public Alien() {
    }

    /**
     * Constructor for the Alien class with an ID parameter.
     *
     * @param id The identifier of the alien.
     */
    public Alien(int id) {
        super(id);
    }

    /**
     * Gets the name of the alien.
     *
     * @return The name of the alien.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the alien.
     *
     * @param name The name of the alien.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the alien.
     *
     * @return The description of the alien.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the alien.
     *
     * @param description The description of the alien.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the logo of the alien.
     *
     * @return The logo of the alien.
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the logo of the alien.
     *
     * @param logo The logo of the alien.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets the full-size logo of the alien.
     *
     * @return The full-size logo of the alien.
     */
    public String getFullSizeLogo() {
        return fullSizeLogo;
    }

    /**
     * Sets the full-size logo of the alien.
     *
     * @param fullSizeLogo The full-size logo of the alien.
     */
    public void setFullSizeLogo(String fullSizeLogo) {
        this.fullSizeLogo = fullSizeLogo;
    }

    /**
     * Gets the first appearance year of the alien.
     *
     * @return The first appearance year of the alien.
     */
    public int getFirstAppearance() {
        return firstAppearance;
    }

    /**
     * Sets the first appearance year of the alien.
     *
     * @param firstAppearance The first appearance year of the alien.
     */
    public void setFirstAppearance(int firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    /**
     * Gets the number of likes received by the alien.
     *
     * @return The number of likes received by the alien.
     */
    public int getLikesCount() {
        return likesCount;
    }

    /**
     * Sets the number of likes received by the alien.
     *
     * @param likesCount The number of likes received by the alien.
     */
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    /**
     * Gets the planet associated with the alien.
     *
     * @return The planet associated with the alien.
     */
    public Planet getPlanet() {
        return planet;
    }

    /**
     * Sets the planet associated with the alien.
     *
     * @param planet The planet associated with the alien.
     */
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    /**
     * Gets the ability of the alien.
     *
     * @return The ability of the alien.
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Sets the ability of the alien.
     *
     * @param ability The ability of the alien.
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    /**
     * Gets the list of sources related to the alien.
     *
     * @return The list of sources related to the alien.
     */
    public List<Source> getSourceList() {
        return sourceList;
    }

    /**
     * Sets the list of sources related to the alien.
     *
     * @param sourceList The list of sources related to the alien.
     */
    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    /**
     * Gets the list of comments related to the alien.
     *
     * @return The list of comments related to the alien.
     */
    public List<Comment> getCommentList() {
        return commentList;
    }

    /**
     * Sets the list of comments related to the alien.
     *
     * @param commentList The list of comments related to the alien.
     */
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
        Alien alien = (Alien) o;

        return firstAppearance == alien.firstAppearance &&
                likesCount == alien.likesCount &&
                Objects.equals(name, alien.name) &&
                Objects.equals(description, alien.description) &&
                Objects.equals(logo, alien.logo) &&
                Objects.equals(fullSizeLogo, alien.fullSizeLogo) &&
                Objects.equals(planet, alien.planet) &&
                Objects.equals(ability, alien.ability) &&
                Objects.equals(sourceList, alien.sourceList) &&
                Objects.equals(commentList, alien.commentList);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, logo, fullSizeLogo, firstAppearance, likesCount, planet, ability, sourceList, commentList);
    }

    /**
     * Returns a string representation of the Alien object.
     *
     * @return A string representation of the Alien object.
     */
    @Override
    public String toString() {
        return "Alien{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", fullSizeLogo='" + fullSizeLogo + '\'' +
                ", firstAppearance=" + firstAppearance +
                ", likesCount=" + likesCount +
                ", planet=" + planet +
                ", ability=" + ability +
                ", sourceList=" + sourceList +
                ", commentList=" + commentList +
                '}';
    }

    /**
     * Represents an implementation of the Builder design pattern.
     */
    public static class Builder {

        private Alien newAlien;

        public Builder() {
            newAlien = new Alien();
        }

        public Builder withId(int id) {
            newAlien.id = id;
            return this;
        }

        public Builder withName (String name) {
            newAlien.name = name;
            return this;
        }

        public Builder withDescription (String description) {
            newAlien.description = description;
            return this;
        }
        public Builder withLogo (String logo) {
            newAlien.logo = logo;
            return this;
        }
        public Builder withFullSizeLogo (String fullSizeLogo) {
            newAlien.fullSizeLogo = fullSizeLogo;
            return this;
        }
        public Builder withFirstAppearance (int firstAppearance) {
            newAlien.firstAppearance = firstAppearance;
            return this;
        }
        public Builder withLikesCount (int likesCount) {
            newAlien.likesCount = likesCount;
            return this;
        }
        public Builder withPlanet (Planet planet) {
            newAlien.planet = planet;
            return this;
        }
        public Builder withAbility (Ability ability) {
            newAlien.ability = ability;
            return this;
        }
        public Builder withSources (List<Source> sources) {
            newAlien.sourceList = sources;
            return this;
        }
        public Builder withComments (List<Comment> comments) {
            newAlien.commentList = comments;
            return this;
        }

        public Alien build() {
            return newAlien;
        }
    }
}
