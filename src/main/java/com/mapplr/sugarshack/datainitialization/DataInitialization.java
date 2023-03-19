//package com.mapplr.sugarshack.datainitialization;
//
//import com.mapplr.sugarshack.model.Cart;
//import com.mapplr.sugarshack.model.CartItem;
//import com.mapplr.sugarshack.model.MapleSyrup;
//import com.mapplr.sugarshack.model.SyrupType;
//import com.mapplr.sugarshack.repository.CartItemRepository;
//import com.mapplr.sugarshack.repository.CartRepository;
//import com.mapplr.sugarshack.repository.OrderRepository;
//import com.mapplr.sugarshack.repository.SyrupRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.time.ZonedDateTime;
//import java.util.Arrays;
//
//@Component
//public class DataInitialization implements CommandLineRunner {
//
//    @Autowired
//    private SyrupRepository mapleSyrupRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create Maple Syrups
//        MapleSyrup mapleSyrup1 = new MapleSyrup();
//        mapleSyrup1.setType(SyrupType.AMBER);
//        mapleSyrup1.setName("Amber Maple Syrup");
//        mapleSyrup1.setDescription("Grade A");
//        mapleSyrup1.setPrice(10.0);
//        mapleSyrup1.setStock(100);
//        mapleSyrup1.setImage("https://www.example.com/ambersyrup.jpg");
//
//        MapleSyrup mapleSyrup2 = new MapleSyrup();
//        mapleSyrup2.setType(SyrupType.DARK);
//        mapleSyrup2.setName("Dark Maple Syrup");
//        mapleSyrup2.setDescription("Grade B");
//        mapleSyrup2.setPrice(12.0);
//        mapleSyrup2.setStock(50);
//        mapleSyrup2.setImage("https://www.example.com/darksyrup.jpg");
//
//        MapleSyrup mapleSyrup3 = new MapleSyrup();
//        mapleSyrup2.setType(SyrupType.CLEAR);
//        mapleSyrup2.setName("Clean Maple Syrup");
//        mapleSyrup2.setDescription("Grade C");
//        mapleSyrup2.setPrice(15.0);
//        mapleSyrup2.setStock(10);
//        mapleSyrup2.setImage("https://www.example.com/clearsyrup.jpg");
//
//        mapleSyrupRepository.saveAll(Arrays.asList(mapleSyrup1, mapleSyrup2, mapleSyrup3));
//    }
//}
//
