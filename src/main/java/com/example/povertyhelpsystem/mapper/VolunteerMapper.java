package com.example.povertyhelpsystem.mapper;

import com.example.povertyhelpsystem.pojo.Poverty;
import com.example.povertyhelpsystem.pojo.Volunteer;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface VolunteerMapper {

    Integer count(@Param("name") String name,
                  @Param("age") Integer age,
                  @Param("gender") Integer gender,
                  @Param("volunteerId") String volunteerId,
                  @Param("begin") LocalDate begin,
                  @Param("end") LocalDate end);


    // 分页查询
    List<Volunteer> page(
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("gender") Integer gender,
            @Param("volunteerId") String volunteerId,
            @Param("begin") LocalDate begin,
            @Param("end") LocalDate end,
            @Param("start") Integer start,
            @Param("pagesize") Integer pagesize
    );

    @Insert("insert into volunteer(name, age, gender, volunteerId, comeDate, help, povertyNumber) " +
            "values(#{name}, #{age}, #{gender}, #{volunteerId}, #{comeDate}, #{help} ,#{povertyNumber})")
    void insertVolunteer(Volunteer volunteer);

    void updateVolunteer(Volunteer volunteer);

    void updatePoverty_volunteerId_by_updateVolunteer(Volunteer volunteer);

    @Select("select povertyId from poverty where poverty.volunteerId = #{volunteerId}")
    List<String> select_povertyId_by_volunteerId(String volunteerId);
    @Update("update poverty set volunteerId = null where poverty.povertyId = #{povertyId}")
    void updatePovertyVolunteerId_ToNull_by_deleteVolunteer(String povertyId);
    @Delete("delete from volunteer where volunteer.volunteerId = #{volunteerId}")
    void deleteVolunteer(String volunteerId);
}