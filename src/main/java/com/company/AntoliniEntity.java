package com.company;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "antolini", schema = "shop_data")
public class AntoliniEntity {
    private String productName;
    private String productPrice;
    private String productCategory;
    private String productLink;
    private Timestamp lastUpdate;
    private int id;

    @Basic
    @Column(name = "product_name", nullable = true, length = 1024)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_price", nullable = true, length = 255)
    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_category", nullable = true, length = 1024)
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Basic
    @Column(name = "product_link", nullable = true, length = 4096)
    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    @Basic
    @Column(name = "last_update", nullable = true)
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AntoliniEntity that = (AntoliniEntity) o;

        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        if (productCategory != null ? !productCategory.equals(that.productCategory) : that.productCategory != null)
            return false;
        if (productLink != null ? !productLink.equals(that.productLink) : that.productLink != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        result = 31 * result + (productLink != null ? productLink.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
