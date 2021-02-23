package product;

import VO.ProductVO;
import bean.Product;
import com.github.pagehelper.PageInfo;
import com.jiang.base.IBaseService;

import java.util.List;

public interface IProductService extends IBaseService<Product> {
    PageInfo<Product> page(Integer pageIndex, Integer pageSize);

    Long add(ProductVO productVO);

    List<Product> searchByTypeId(Long TypeId);

}
