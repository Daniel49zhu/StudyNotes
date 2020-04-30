package mapstructdemo.convert;

import mapstructdemo.bo.UserBO;
import mapstructdemo.bo.UserDetailBO;
import mapstructdemo.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);

    @Mappings({@Mapping(source = "id", target = "userId")})
    UserDetailBO convertDetail(UserDO userDO);
}
