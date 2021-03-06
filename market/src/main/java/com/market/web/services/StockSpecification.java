package com.market.web.services;

import com.market.domain.valueobjects.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class StockSpecification implements Specification<Stock> {

    @Getter
    private StockSearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.getCategories() != null && !searchCriteria.getCategories().isEmpty()) {
            predicates.add(criteriaBuilder.isTrue(root.get(Stock.CATEGORY).in(searchCriteria.getCategories())));
        }
        if (searchCriteria.getPrice() != null) {
            predicates.add(criteriaBuilder.lessThan(root.get(Stock.PRICE), searchCriteria.getPrice()));
        }
        if (searchCriteria.getDiscount() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Stock.DISCOUNT), searchCriteria.getDiscount()));
        }
        if (searchCriteria.getTitle() != null) {
            predicates.add(criteriaBuilder.like(root.get(Stock.TITLE), "%" + searchCriteria.getTitle() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
