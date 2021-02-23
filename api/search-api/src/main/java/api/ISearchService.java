package api;

import com.jiang.pojo.ResultBean;

public interface ISearchService {
    //全量同步，在数据初始化时使用
    public ResultBean synAllData();

    //增量同步
    public ResultBean synById(Long id);

    //精确删除solr中某一条记录
    public ResultBean delById(Long id);

    //通过关键字查询
    public ResultBean queryByKeywords(String keywords);

    ResultBean queryByKeywords(String keyWords, Integer pageIndex, Integer pageSize);
}
