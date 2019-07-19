package com.yem.base.dao;

import com.yem.entity.YemMcCode;

public interface YemMcCodeMapper {
    int deleteByPrimaryKey(String mccodeId);

    int insert(YemMcCode record);

    int insertSelective(YemMcCode record);

    YemMcCode selectByMcCodeType(String mccodeType);

    int updateByPrimaryKeySelective(YemMcCode record);

    int updateByPrimaryKey(YemMcCode record);
}