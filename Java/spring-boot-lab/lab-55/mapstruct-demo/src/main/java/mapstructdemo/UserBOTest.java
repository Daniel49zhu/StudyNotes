package mapstructdemo;

import mapstructdemo.bo.UserBO;
import mapstructdemo.bo.UserDetailBO;
import mapstructdemo.convert.UserConvert;
import mapstructdemo.dataobject.UserDO;

public class UserBOTest {
    public static void main(String[] args) {
        UserDO userDO = new UserDO()
                .setId(1)
                .setUsername("yudaoyuanma")
                .setPassword("1024");

        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        System.out.println(userBO);

        UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetail(userDO);
        System.out.println(userDetailBO);
    }
}
