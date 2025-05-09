package com.carlos.curso.springboot.di.factura.pringboot_difactura.controllers;

import com.carlos.curso.springboot.di.factura.pringboot_difactura.models.Client;
import com.carlos.curso.springboot.di.factura.pringboot_difactura.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show() {
        Client c = new Client();
        c.setName(invoice.getClient().getName());
        c.setLastName(invoice.getClient().getLastName());

        Invoice i = new Invoice();
        i.setClient(c);
        i.setDesciption(invoice.getDesciption());
        i.setItems(invoice.getItems());
        return i;
    }
}
