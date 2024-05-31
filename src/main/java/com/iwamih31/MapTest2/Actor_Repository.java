package com.iwamih31.MapTest2;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Actor_Repository extends JpaRepository<Actor, Integer> {}

