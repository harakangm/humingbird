package com.humingbird.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.humingbird.dto.ItemDto;
import com.humingbird.dto.ItemSearchDto;
import com.humingbird.dto.QItemDto;
import com.humingbird.entity.QNewItem;
import com.humingbird.entity.QNewItemImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class NewItemRepositoryCustomImpl implements NewItemRepositoryCustom {

	
	private JPAQueryFactory queryFactory;
	
	public NewItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression clotheNameLike(String searchQuery) {//검색
		return StringUtils.isEmpty(searchQuery) ? null : QNewItem.newItem.clotheName.like("%" + searchQuery + "%");
	}
	
	private BooleanExpression brandNameLike(String searchQuery) { //검색
		return StringUtils.isEmpty(searchQuery) ? null : QNewItem.newItem.brandName.like("%" + searchQuery + "%");
	}
	
	@Override
	public Page<ItemDto> getMainItemPage(ItemSearchDto itemserchDto,Pageable pageable) {
        QNewItem item = QNewItem.newItem;
        QNewItemImg itemImg = QNewItemImg.newItemImg;

        List<ItemDto> content = queryFactory
                .select(
                        new QItemDto(
                                item.id,
                                item.brandName,
                                item.clotheName,
                                item.detail,
                                item.price,
                                itemImg.imgUrl)
                )
                .from(itemImg)
                .join(itemImg.newitem, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(clotheNameLike(itemserchDto.getSearchQuery()))
                .where(brandNameLike(itemserchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        		
        long total = queryFactory
                .select(Wildcard.count)
                .from(itemImg)
                .join(itemImg.newitem, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(clotheNameLike(itemserchDto.getSearchQuery()))
                .where(brandNameLike(itemserchDto.getSearchQuery()))
                .fetchOne()
                ;

        return new PageImpl<>(content, pageable, total);
	}
	

}
