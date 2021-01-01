package com.simpledevelopment.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {

	public Integer getOnhandInventory(UUID beerId);
}
