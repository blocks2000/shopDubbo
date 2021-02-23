package api;

import com.jiang.pojo.ResultBean;

public interface ICartService {
    //增删改查购物车时，需要一个唯一标识符

    ResultBean add(String token,Long productId,Integer count);

    ResultBean del(String token,Long productId);

    ResultBean update(String token,Long productId,Integer count);

    ResultBean query(String token);

    ResultBean merge(String nologinKey,String loginKey);
}
