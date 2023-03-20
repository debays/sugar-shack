package com.mapplr.sugarshack;

import com.mapplr.sugarshack.model.MapleSyrup;
import com.mapplr.sugarshack.repository.SyrupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SugarShackApplicationTests {

    @Autowired
    private SyrupRepository syrupRepository;

    @Test
    public void testFindAll() {
        // test the find all method
        List<MapleSyrup> syrups = syrupRepository.findAll();
        assertEquals(3, syrups.size());

        // test the details of the first syrup
        MapleSyrup firstSyrup = syrups.get(0);
        assertEquals("Pure Amber Maple Syrup", firstSyrup.getName());
        assertEquals("Grade A, dark color and robust taste", firstSyrup.getDescription());
        assertEquals(15.99, firstSyrup.getPrice(), 0.001);
        assertEquals(100, firstSyrup.getStock());
    }
}