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
@Table(name = "data_info")
public class Data_Info {
  // ID
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
   // データキー
  @Column(name = "data_key", nullable = true)
  private String data_Key;
  // マップナンバー
  @Column(name = "map_number", nullable = true)
  private Integer map_Number;
  // x座標
  @Column(name = "x", nullable = true)
  private Integer x;
  // y座標
  @Column(name = "y", nullable = true)
  private Integer y;
}
