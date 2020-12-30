package com.half.boot05webadmin.mapper;

import com.half.boot05webadmin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/29 11:04
 * @Version 1.0
 * @Description
 */
@Mapper
public interface AccountMapper {
        public Account findOne(Integer id);
}
