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
@Table(name = "message")
public class Message {
  // ID
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false)
  private String text;
}
