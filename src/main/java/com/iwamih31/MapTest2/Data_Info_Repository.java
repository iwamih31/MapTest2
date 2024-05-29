package com.iwamih31.MapTest2;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Data_Info_Repository extends JpaRepository<Data_Info, Integer> {
  /** data_Key 取得 */
	@Query("select data_key"
			+ " from Data_Info info"
			+ " where info.id =:data_Id")
	public String data_Key(
		@Param("data_Id") Integer data_Id
  );
}

