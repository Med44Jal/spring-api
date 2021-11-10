package com.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "per")
    private double per;

    @Column(name = "description")
    private String description;

    @Column(name = "instock")
    private long instock;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategory_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Subcategory subcategory;

    public Product() {
    }

    public Product(String name, double price, double per, String description, long instock, byte[] image, Subcategory subcategory) {
        this.name = name;
        this.price = price;
        this.per = per;
        this.description = description;
        this.instock = instock;
        this.subcategory = subcategory;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInstock() {
        return instock;
    }

    public void setInstock(long instock) {
        this.instock = instock;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
