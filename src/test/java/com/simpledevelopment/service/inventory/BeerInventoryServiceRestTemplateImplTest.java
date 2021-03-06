package com.simpledevelopment.service.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.simpledevelopment.bootstrap.BeerLoader;

@Disabled // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

	@Autowired
	private BeerInventoryService beerInventoryService;

	@BeforeEach
	void setUp() {
	}

	@Test
	void getOnhandInventory() {
		Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);
		System.out.println(qoh);
	}
}