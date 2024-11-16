package com.example.povertyhelpsystem.mapper;

import com.example.povertyhelpsystem.pojo.Poverty;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PovertyMapper {

    Integer count(@Param("name") String name,
                  @Param("age") Integer age,
                  @Param("gender") Integer gender,
                  @Param("povertyId") String povertyId,
                  @Param("begin") LocalDate begin,
                  @Param("end") LocalDate end);


    // 分页查询
    List<Poverty> page(
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("gender") Integer gender,
            @Param("povertyId") String povertyId,
            @Param("begin") LocalDate begin,
            @Param("end") LocalDate end,
            @Param("start") Integer start,
            @Param("pagesize") Integer pagesize
    );


    void insertPoverty(Poverty poverty);

    void updateVolunteer_PovertyNumber_by_updatePoverty(Poverty poverty);
    void updatePoverty(Poverty poverty);


    @Delete("update volunteer set povertyNumber = povertyNumber - 1" +
            " where volunteerId = #{volunteerId}")
    void deleteVolunteer_PovertyNumber_by_deletePoverty(String volunteerId);
    @Delete("delete from poverty where povertyId = #{povertyId}")
    void deletePoverty(String povertyId);
    @Select("select volunteerId from poverty where povertyId = #{povertyId}")
    String select_volunteerId_by_povertyId(String povertyId);
}
