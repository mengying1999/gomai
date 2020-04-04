package com.gomai.user.mapper;

import com.gomai.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;


//tk.mybatis.mapper.common.Mapper通用mapper

public interface UserMapper extends Mapper<User> {
    @Select("select * from user where u_id=#{uId}")
    public User SelectUAll(int uId);

    @Update("update user set u_id=#{uId},u_name=#{uName},u_password=#{uPassword},u_sex=#{uSex},u_avatar=#{uAvatar},u_identity=#{uIdentity},u_school=#{uSchool},u_llike=#{uLlike},u_birthday=#{uBirthday},u_phone=#{uPhone},u_atatus=#{uAtatus}}) {\n")
    public Boolean UpdateU(User user);

    @Delete("delete from user where u_id=#{uId}")
    public Boolean DeleteU(int uId);

    @Insert("insert into user value(null,u_name=#{uName},u_password=#{uPassword},u_sex=#{uSex},u_avatar=#{uAvatar},u_identity=#{uIdentity},u_school=#{uSchool},u_llike=#{uLlike},u_birthday=#{uBirthday},u_phone=#{uPhone},u_atatus=#{uAtatus}}) {\n")
    public Boolean AddU(int uId);

}
