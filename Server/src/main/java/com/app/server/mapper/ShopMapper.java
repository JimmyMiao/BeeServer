package com.app.server.mapper;

import com.app.server.model.ShopModel;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface ShopMapper {
    /**
     * 批量插入List
     * insertAllTaobao是RestProvider中的一个方法，这里相当于反射
     * Param("list")中的list必须与insertAllTaobao中的list名字一致
     */
    /*@InsertProvider(type = RestProvider.class, method = "insertAllBusiness")
    void insertAll(@Param("list") List<BusinessModel> users);*/

    /**
     * 清空所有记录，并重新定义index
     */
    @Select("TRUNCATE TABLE shop")
    void deleteAll();


    @Select("select * from shop where business_id = 1")
    ArrayList<ShopModel> selectAll1();

    @Select("select * from shop where business_id = 2")
    ArrayList<ShopModel> selectAll2();
}
