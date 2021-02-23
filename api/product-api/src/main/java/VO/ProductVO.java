package VO;

import bean.Product;

import java.io.Serializable;

public class ProductVO implements Serializable {
    private Product product;
    private String productDesc;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
