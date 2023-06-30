package com.olsh4u.epam.models.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent search form which is used to build a dynamic search query.
 */
public class SearchForm {

    private String query;
    private List<Integer> sources;
    private List<Integer> ability;
    private List<Integer> planet;

    public SearchForm(String query, String[] sources, String[] ability, String[] planet) {
        this.query = query;
        this.sources = convert(sources);
        this.ability = convert(ability);
        this.planet = convert(planet);
    }

    private List<Integer> convert(String[] str) {
        if (str == null) {
            return null;
        } else {
            List<Integer> resultList = new ArrayList<>();
            for (String value: str) {
                resultList.add(Integer.parseInt(value));
            }
            return resultList;
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getSources() {
        return sources;
    }

    public void setSources(List<Integer> sources) {
        this.sources = sources;
    }

    public List<Integer> getAbility() {
        return ability;
    }

    public void setAbility(List<Integer> ability) {
        this.ability = ability;
    }

    public List<Integer> getPlanet() {
        return planet;
    }

    public void setPlanet(List<Integer> planet) {
        this.planet = planet;
    }
}
