package com.humingbird.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humingbird.entity.TopNewItem;

public interface TopNewItemRepository extends JpaRepository<TopNewItem, Long>{
	List<TopNewItem> findByItemIdOrderByIdAsc(Long ItemId);
}
