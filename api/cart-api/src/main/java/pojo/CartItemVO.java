package pojo;

import bean.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//跟视图层对应的对象

public class CartItemVO implements Serializable {

    private Product product;

    private Integer count;

    private Date updateTime;

    public CartItemVO(Product product, Integer count, Date updateTime) {
        this.product = product;
        this.count = count;
        this.updateTime = updateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
