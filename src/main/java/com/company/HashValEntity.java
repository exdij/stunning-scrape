package com.company;

import javax.persistence.*;

@Entity
@Table(name = "hash_val", schema = "shop_data")
public class HashValEntity {
    private String antolini;
    private int id;


    @Basic
    @Column(name = "antolini", nullable = true, length = 1024)
    public String getAntolini() {
        return antolini;
    }

    public void setAntolini(String antolini) {
        this.antolini = antolini;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashValEntity that = (HashValEntity) o;

        if (antolini != null ? !antolini.equals(that.antolini) : that.antolini != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return antolini != null ? antolini.hashCode() : 0;
    }

    @Id
    @Column(name = " id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
