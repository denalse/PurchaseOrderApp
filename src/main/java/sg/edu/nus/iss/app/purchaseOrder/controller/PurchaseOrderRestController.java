package sg.edu.nus.iss.app.purchaseOrder.controller;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.app.purchaseOrder.model.Order;
import sg.edu.nus.iss.app.purchaseOrder.service.Quotation;
import sg.edu.nus.iss.app.purchaseOrder.service.QuotationService;

@RestController
@RequestMapping(path="/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderRestController {

    @Autowired
    private Quotation quote;

    @Autowired
    private Order order;

    @GetMapping(path="{quoteId}")
    public ResponseEntity<String> getQuoteId(
            @PathVariable(name="quoteId") String quoteId) {
            
        final Quotation qId = quote.get(quoteId);

        // final JsonObject resp = Json.createObject()
        //     .add(“”, user.getName())
        //     .build();
        
        @PostMapping(consumes="application/json")
        public ResponseEntity<String> postUser(@RequestBody String payload, @RequestHeader ("User-Agent") String userAgent ) {
    
            JsonObject object;
    
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader read = Json.createReader(is);
            object = read.readObject();
    
        } catch (Exception ex) {
            object = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
            return ResponseEntity.internalServerError().body(object.toString());
        }
        
        private double appleCost = 0.3;
        private double durianCost = 0.5;
        private double grapesCost = 0.7;
        private double orangeCost = 0.9;
        private double pearCost = 1.1;


        Order order = new order(itemName * appleCost);

        JsonObjectBuilder calBuilder = Json.createObjectBuilder();

        calBuilder.add("invoiceId", invoiceId)
            .add("name", name)
            .add("total", total);

        JsonObject cal = calBuilder.build();

        return ResponseEntity.ok("200");
            }

    // private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderRestController.class);

    // logger.info("  Order >>> " + purchaseOrder1);


}
