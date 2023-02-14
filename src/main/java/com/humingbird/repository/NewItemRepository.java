package com.humingbird.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humingbird.entity.NewItem;

public interface NewItemRepository extends JpaRepository<NewItem, Long>, NewItemRepositoryCustom{
		
	
}
