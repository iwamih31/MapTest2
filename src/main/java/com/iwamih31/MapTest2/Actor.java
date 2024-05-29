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
  @Column(nullable = false)
  private Integer data_Id;
  @Column()
  private String actor_name;
  @Column()
  private String role;
  // @Column()
  // private Integer no;
  @Column()
  private Integer exp;
  @Column()
  private int lev;
  @Column()
  private Integer hp;
  @Column()
  private Integer mp;
  // @Column()
  // private int sp;
  @Column()
  private Integer wp;
//  @Column()
  // private int ap;
//  @Column()
  // private int ep;
}
