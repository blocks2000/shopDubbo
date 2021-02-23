package api;

import bean.TUser;
import com.jiang.base.IBaseService;
import com.jiang.pojo.ResultBean;

public interface IUserService extends IBaseService<TUser> {
    //判断用户名是否已经存在
    ResultBean checkUsernameIsExists(String username);
    //判断邮箱是否已经存在
    ResultBean checkEmailIsExists(String email);
    //判断电话号码是否已经存在
    ResultBean checkPhoneIsExists(String phone);

    //添加用户，可以用默认的实现
    //激活用户，改变用户状态，也可以用默认的修改方法进行修改
    ResultBean generateCode(String identification);

    ResultBean checkLogin(TUser user);

    ResultBean checkIsLogin(String uuid);
}
