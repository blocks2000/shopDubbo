package mapper;

import bean.TUser;
import bean.TUserExample;
import java.util.List;

import com.jiang.base.IBaseDao;
import org.apache.ibatis.annotations.Param;

//用户的操作dao层，也是直接继承BaseDao
public interface TUserMapper extends IBaseDao<TUser> {
     TUser selectByUserName(String username);
    TUser selectByPhone(String phone);
    TUser selectByEmail(String email);

    TUser selectByIdentification(String username);
}