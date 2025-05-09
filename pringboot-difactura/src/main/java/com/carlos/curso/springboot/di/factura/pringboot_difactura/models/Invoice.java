package com.carlos.curso.springboot.di.factura.pringboot_difactura.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
//@ApplicationScope
//@JsonIgnoreProperties({ "targetSource", "advisors" })
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.office}")
    private String desciption;

    @Autowired
    @Qualifier("itemInvoiceOffice")
    private List<Item> items;

    @PostConstruct
    public void init() {
        System.out.println("Creando el componente de la factura");
        client.setName(client.getName().concat(" Luis"));
        desciption = desciption.concat(" del cliente: ").concat(client.getName()).concat(" ").concat(client.getLastName());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Desrtuyendo el componente o bean invoice");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        //        int total = 0;
        //        for (Item item : items) {
        //            total += item.getImporte();
        //        }

        return items.stream().map(item -> item.getImporte()).reduce(0, (sum, importe) -> sum + importe);
    }
}
