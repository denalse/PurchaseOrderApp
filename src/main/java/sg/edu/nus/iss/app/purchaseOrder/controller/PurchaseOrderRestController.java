package sg.edu.nus.iss.app.purchaseOrder.controller;


import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.app.purchaseOrder.service.Quotation;
import sg.edu.nus.iss.app.purchaseOrder.service.QuotationService;

@RestController
@RequestMapping(path="/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderRestController {

    @Autowired
    private Quotation quote;

    @GetMapping(path="{quoteId}")
    public ResponseEntity<String> getQuoteId(
            @PathVariable(name="quoteId") String quoteId) {
            
        final Quotation qId = quote.get(quoteId);

        // final JsonObject resp = Json.createObject()
        //     .add(“”, user.getName())
        //     .build();

        return ResponseEntity.ok("200");
            }

    // private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderRestController.class);

    // logger.info("  Order >>> " + purchaseOrder1);


}
