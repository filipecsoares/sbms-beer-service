package com.simpledevelopment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simpledevelopment.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
