package com.redhat.fuse.demo.controller;

import com.redhat.fuse.demo.Order;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private FluentProducerTemplate template;

    public DemoController(FluentProducerTemplate template){

        this.template = template;
    }


    @PostMapping(value = "/purchases/car/{quantity}")
    public Order requestItem(@PathVariable("quantity") Integer quantity) {


        return  template.withHeader("itemId", "car").withHeader("quantity", quantity).to("direct:controllerTest").request(Order.class);
    }
}
