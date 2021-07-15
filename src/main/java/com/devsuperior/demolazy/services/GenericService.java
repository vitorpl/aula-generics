package com.devsuperior.demolazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.devsuperior.demolazy.util.Convertible;

//@ Service
public interface GenericService<T extends Convertible<DTO>, DTO, ID> {

	//@Autowired
	//private JpaRepository<T, ID> repository;
	JpaRepository<T, ID> getRepository();
	
	default DTO findById(ID id) {
		Optional<T> result = getRepository().findById(id);
		return result.get().convert();
	}
	
	default List<DTO> findAll() {
		List<T> list = getRepository().findAll();
		return list.stream().map(emp -> emp.convert()).collect(Collectors.toList());
	}
	
}
