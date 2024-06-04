package com.iwamih31.MapTest2;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Role_Repository extends JpaRepository<Role, Integer> {

  	/**	Party_Info リスト取得（data_id 指定 the_day 順） */
	@Query("select role"
			+ " from Role role"
			+ " where role.role_Name = :role_Name")
  Role role(@Param("role_Name") String role_Name);

}

