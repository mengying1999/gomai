package com.gomai.user.api;


import com.gomai.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jl
 * @description
 * @date 2019-11-05 15:58
 */
public interface UserApi {

    @GetMapping("/query")
    User queryBy(@RequestParam("uName") String uName, @RequestParam("uPassword") String uPassword);
}
