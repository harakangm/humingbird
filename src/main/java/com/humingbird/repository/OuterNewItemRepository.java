package com.humingbird.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humingbird.entity.OuterNewItem;

public interface OuterNewItemRepository extends JpaRepository<OuterNewItem, Long>{
	List<OuterNewItem> findByItemIdOrderByIdAsc(Long itemId);
}
