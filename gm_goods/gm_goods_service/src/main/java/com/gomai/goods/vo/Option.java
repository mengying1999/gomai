package com.gomai.goods.vo;

import java.util.List;

public class Option {
    public Integer value;
    public String label;
    public List<Option> children;

    public Option() {
    }

    public Option(Integer value, String label, List<Option> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }

    public Option(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Option> getChildren() {
        return children;
    }

    public void setChildren(List<Option> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Option{" +
                "value=" + value +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
