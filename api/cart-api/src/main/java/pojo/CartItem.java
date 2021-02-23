package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//保存在redis购物车中的结构

public class CartItem implements Serializable,Comparable<CartItem> {

    private Long productId;

    private Integer count;

    private Date updateTime;

    public CartItem() {
    }

    public CartItem(Long productId, Integer count, Date updateTime) {
        this.productId = productId;
        this.count = count;
        this.updateTime = updateTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    //返回值小于0，放左边，大于0，放右边,等于0，则直接覆盖
    //树的展示顺序是左中右
    @Override
    public int compareTo(CartItem cartItem) {
        int result=(int) (cartItem.getUpdateTime().getTime()-this.getUpdateTime().getTime());
        if (result==0)
        {
            return -1;
        }
        return result;
    }
}
