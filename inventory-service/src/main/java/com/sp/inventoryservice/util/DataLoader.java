//package com.sp.inventoryservice.util;
//
//import com.sp.inventoryservice.model.Inventory;
//import com.sp.inventoryservice.repository.InventoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//    private final InventoryRepository inventoryRepository;
//    @Override
//    public void run(String... args) throws Exception {
//        Inventory inventory = new Inventory();
//        inventory.setSkuCode("iPhone 13");
//        inventory.setQuantity(100);
//
//        Inventory inventory1 = new Inventory();
//        inventory1.setSkuCode("iphone 13 red");
//        inventory1.setQuantity(50);
//
//        inventoryRepository.save(inventory);
//        inventoryRepository.save(inventory1);
//    }
//}
