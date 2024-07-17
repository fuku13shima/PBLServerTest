package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entry.Company;

public interface CompanyRepository extends CrudRepository<Company , Integer>{

}
