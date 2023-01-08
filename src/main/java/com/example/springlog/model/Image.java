package com.example.springlog.model;

import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFile")
    private String originalFile;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;
    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Product product;

    public Image(long id, String name, String originalFile, Long size, String contentType, boolean isPreviewImage, byte[] bytes, Product product) {
        this.id = id;
        this.name = name;
        this.originalFile = originalFile;
        this.size = size;
        this.contentType = contentType;
        this.isPreviewImage = isPreviewImage;
        this.bytes = bytes;
        this.product = product;
    }

    public Image() {

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

    public String getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(String originalFile) {
        this.originalFile = originalFile;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isPreviewImage() {
        return isPreviewImage;
    }

    public void setPreviewImage(boolean previewImage) {
        isPreviewImage = previewImage;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
