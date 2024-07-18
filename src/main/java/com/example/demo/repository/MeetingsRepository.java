package com.example.demo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Meetings;

public interface MeetingsRepository extends CrudRepository<Meetings , Integer>{

	Iterable<Meetings> findAll(Sort by);

}
