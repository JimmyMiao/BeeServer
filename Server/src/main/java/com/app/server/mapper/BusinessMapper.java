package com.app.server.mapper;

import com.app.server.model.BusinessModel;
import com.app.server.request.RestProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface BusinessMapper {
    /**
     * 批量插入List
     * insertAllTaobao是RestProvider中的一个方法，这里相当于反射
     * Param("list")中的list必须与insertAllTaobao中的list名字一致
     */
    @InsertProvider(type = RestProvider.class, method = "insertAllBusiness")
    void insertAll(@Param("list") List<BusinessModel> users);

    /**
     * 清空所有记录，并重新定义index
     */
    @Select("TRUNCATE TABLE business")
    void deleteAll();


    @Select("select * from business")
    ArrayList<BusinessModel> selectAll();
}
