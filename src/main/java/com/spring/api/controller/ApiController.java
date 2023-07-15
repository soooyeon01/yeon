package com.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;
import com.spring.api.service.PetService;
import com.spring.api.service.ShelService;
import com.spring.api.service.WithService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiController {
    private final PetService service;

    @GetMapping("petdata")
    public void fetchDataAndSave() {
        service.fetchDataAndSaveToDB();
         
    }
}

	