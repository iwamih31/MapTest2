package com.iwamih31.MapTest2;

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

  private Integer data_Id;

  private String actor_name;

  private String role;

  // private Integer no;

  private Integer exp;

  private int lev;

  private Integer hp;

  private Integer mp;

  // private int sp;

  private Integer wp;

  // private int ap;

  // private int ep;
}
