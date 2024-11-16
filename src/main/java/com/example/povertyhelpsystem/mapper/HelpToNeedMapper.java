package com.example.povertyhelpsystem.mapper;

import com.example.povertyhelpsystem.pojo.PTV;
import com.example.povertyhelpsystem.pojo.Poverty;
import com.example.povertyhelpsystem.pojo.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HelpToNeedMapper {

    Integer count(@Param("povertyName") String povertyName,
                  @Param("povertyId") String povertyId);


    // 分页查询
    List<PTV> page(
            @Param("povertyName") String povertyName,
            @Param("povertyId") String povertyId,
            @Param("start") Integer start,
            @Param("pagesize") Integer pagesize);
    //选择未分配志愿者的贫困人员
    @Select("select name,povertyId,need from poverty where volunteerId is null order by length(need) desc ")
    List<Poverty> select_name_id_need();

    @Select("select name,volunteerId,help from volunteer where povertyNumber < 3 order by length(help) desc")
    List<Volunteer> select_name_id_help();

    @Update("update poverty set volunteerId=#{volunteerId} where povertyId=#{povertyId}")
    void update_poverty_volunteerId(@Param("povertyId") String povertyId,
                                    @Param("volunteerId") String volunterrId);

    @Update("update volunteer set povertyNumber=povertyNumber+1 where volunteerId=#{volunteerId}")
    void update_volunteer_povertyNumber(@Param("volunteerId") String volunteerId);

    @Update("update poverty p,volunteer v set p.volunteerId=null,v.povertyNumber=0")
    void update_clear_poverty_volunteerId();
}
