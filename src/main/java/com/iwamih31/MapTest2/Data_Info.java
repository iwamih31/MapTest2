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
  // 
  @Column(name = "data_id", nullable = false)
  private Integer data_Id;
  @Column(name = "data_key", nullable = true)
  private Integer data_Key;
}
