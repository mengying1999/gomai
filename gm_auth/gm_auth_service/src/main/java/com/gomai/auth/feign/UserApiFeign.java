package com.gomai.auth.feign;

import com.gomai.user.api.UserApi;
import com.gomai.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jl
 * @description
 * @date 2019-11-05 16:01
 */
@FeignClient("user-service")
public interface UserApiFeign extends UserApi {
}
