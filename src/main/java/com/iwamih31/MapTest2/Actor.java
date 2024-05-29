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
@Table(name = "actor")
public class Actor {
  // ID
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "data_id", nullable = false)
  private Integer data_Id;
  @Column(name = "actor_name", nullable = true)
  private String actor_Name;
  @Column(nullable = true)
  private String role;
  // @Column(nullable = true)
  // private Integer no;
  @Column(nullable = true)
  private Integer exp;
  @Column(nullable = true)
  private int lev;
  @Column(nullable = true)
  private Integer hp;
  @Column(nullable = true)
  private Integer mp;
  // @Column()
  // private int sp;
  @Column(nullable = true)
  private Integer wp;
//  @Column()
  // private int ap;
//  @Column()
  // private int ep;
}
