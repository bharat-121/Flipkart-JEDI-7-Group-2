package com.flipkart.restController;

import com.flipkart.bean.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerRestController {

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerDetails() {

        //  clinet --- service ---- dao ----> SQL

        Customer customer = new Customer();
        customer.setCustomerID(101);
        customer.setCustomerName("Flipkart");
        customer.setCustomerAddress("mumbai");

        return customer;
    }
}
