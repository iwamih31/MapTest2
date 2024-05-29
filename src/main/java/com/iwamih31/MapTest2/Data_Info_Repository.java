package com.iwamih31.MapTest2;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Data_Info_Repository extends JpaRepository<Data_Info, Integer> {

  	/**	Action リスト取得（date 指定 the_day 順） */
	@Query("select data_key"
			+ " from Data_Info info"
			+ " where info.data_id =:data_id")
	public String data_Key(
			@Param("data_id") Integer data_Id);
}

