package com.iwamih31.MapTest2;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Party_Info_Repository extends JpaRepository<Party_Info, Integer> {

	/**	Party_Info リスト取得（data_id 指定 the_day 順） */
	@Query("select party_info"
			+ " from Party_Info party_info"
			+ " where party_info.data_Id = :data_id"
			+ " order by party_info.no asc")
	public List<Party_Info> list(
			@Param("data_id") Integer data_id);
}

