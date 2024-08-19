package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String index(Model model, Locale locale) {


        return "admin/index";
    }
}