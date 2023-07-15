package com.spring.api.domain;

import javax.xml.bind.annotation.*;

import lombok.Data;

import java.util.List;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<PetnoticeDTO> items;
    @XmlElement(name = "totalCount")
    private int totalCount;

    public List<PetnoticeDTO> getItems() {
        return items;
    }

    public void setItems(List<PetnoticeDTO> items) {
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}