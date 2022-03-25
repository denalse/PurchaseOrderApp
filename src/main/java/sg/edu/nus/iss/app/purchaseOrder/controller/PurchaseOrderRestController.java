package sg.edu.nus.iss.app.purchaseOrder.controller;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
// import sg.edu.nus.iss.app.purchaseOrder.model.Order;
// import sg.edu.nus.iss.app.purchaseOrder.model.Quotation;
import sg.edu.nus.iss.app.purchaseOrder.service.QuotationService;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderRestController.class);

    @Autowired
    private QuotationService quoteSvc;
 
        @PostMapping(path= "/po", consumes="application/json")
        public ResponseEntity<String> postUser(@RequestBody String payload) {
            
            // JsonArray obj = new JsonArray(result);

            JsonObject object = null; 
            // = obj.getJsonObject(0);

        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader read = Json.createReader(is);
            object = read.readObject();         

        } catch (Exception ex) {
            // ex.getStackTrace();
            object = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
        }
        

        List<String> itemList = new ArrayList<String>();
            for (int i = 0; i < object.getJsonArray("lineItems").size(); i++) {
                String itemAdded = object.getJsonArray("lineItems").getJsonObject(i).getString("item");
                itemList.add(itemAdded);

                System.out.println(">>> My List: " + itemAdded );
                logger.info(" >>>> itemList: " + itemList);
                
            // getQuotation auto calculate, no need to define variable
            // do a for loop to retrieve items from payload
            
            }
            return ResponseEntity.ok("200");
        }

    // logger.info("  Order >>> " + purchaseOrder1);


   }

