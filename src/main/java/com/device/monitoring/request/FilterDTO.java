package com.device.monitoring.request;

import java.util.List;
import java.util.Objects;

public class FilterDTO {
    private String name;

    private String value;

    private List<String> values;

    private Condition condition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Condition getClause() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterDTO)) return false;
        FilterDTO filterDTO = (FilterDTO) o;
        return Objects.equals(name, filterDTO.name) &&
                Objects.equals(value, filterDTO.value) &&
                Objects.equals(values, filterDTO.values) &&
                Objects.equals(condition, filterDTO.condition);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value, values, condition);
    }
}
