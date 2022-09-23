package com.redhat.fuse.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {


	@Autowired
	PurchaseService purchaseService;
    

    @Override
    public void configure() throws Exception {

        from("direct:controllerTest")
                .routeId("testRoute")
                .saga().propagation(SagaPropagation.REQUIRED)
                .removeHeader(Exchange.HTTP_URI)
                .setBody(simple("${null}"))
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.HTTP_PATH,simple("${header.itemId}/${header.quantity}"))
                .log("-- Header HTTP_PATH --" + simple("${header.itemId}/${header.quantity}"));
    }
}

