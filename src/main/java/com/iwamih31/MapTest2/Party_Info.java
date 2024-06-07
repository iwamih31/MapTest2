package com.iwamih31.MapTest2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "party_info")
public class Party_Info {
  // ID
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  // データID
  @Column(name = "data_id", nullable = true)
  private Integer data_Id;
  // 順番
  @Column(name = "no", nullable = true)
  private Integer no;
  // actorID
  @Column(name = "actor_id", nullable = true)
  private Integer actor_Id;
}
