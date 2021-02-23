package mapper;

import bean.Product;
import bean.ProductExample;
import java.util.List;

import com.jiang.base.IBaseDao;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends IBaseDao<Product> {

    List<Product> searchByTypeId(Long TypeId);
}