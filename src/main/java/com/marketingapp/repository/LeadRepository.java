package com.marketingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketingapp.entities.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}

//what is repository layer in springboot-- it helps to perform CRUD perform.
// here we have used jparepository not crudrepository because jpa.. has everything
//that crudr.. has but jpa has pagenation features that crud.. don't have.