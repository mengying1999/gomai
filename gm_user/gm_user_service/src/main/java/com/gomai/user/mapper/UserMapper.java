package com.gomai.user.mapper;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;


//tk.mybatis.mapper.common.Mapper通用mapper

public interface UserMapper extends Mapper<User> {
    @Select("select * from user where u_id=#{uId}")
    public User SelectAll(int uId);

    @Update("update user set u_id=#{uId},u_name=#{uName},u_password=#{uPassword},u_sex=#{uSex},u_avatar=#{uAvatar},u_identity=#{uIdentity},u_school=#{uSchool},u_llike=#{uLlike},u_birthday=#{uBirthday},u_phone=#{uPhone},u_atatus=#{uAtatus}}) {\n")
    public void Update(User user);



}
