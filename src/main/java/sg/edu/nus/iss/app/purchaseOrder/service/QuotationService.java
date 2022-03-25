package sg.edu.nus.iss.app.purchaseOrder.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.app.purchaseOrder.model.Quotation;

@Service
public class QuotationService {
    Logger logger = LoggerFactory.getLogger(QuotationService.class);


          
        final String url = "https://quotation.chuklee.com" + "/quotation";
        
    public Optional<Quotation> getQuotations(List<String> items) {
            
            JsonArrayBuilder array = Json.createArrayBuilder();
            // JsonObject jObject = array.getJsonArray("quotations").getJsonObject(i);

            //do a for each loop to say 
            //For each "string" item in the array
            //items to get quotation for..
            for (String item : items) {
                array.add(item);
            }

            JsonArray jsonArray = array.build();

            RequestEntity<String> req = RequestEntity
                    .post(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    // .headers(“Accept”, MediaType.APPLICATION_JSON)
                    .body(jsonArray.toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject object = reader.readObject();
        
            logger.info(" object >>> " + object);

            // System.out.println(object.getJsonObject("address").getJsonObject("email")
            //                     .getJsonArray("lineItems").getJsonObject("name").getJsonObject("navigationId"));

            
            String address = object.getString("address");
            String email = object.getString("email");
            JsonArray listItems = object.getJsonArray("listItems");           
            String name = object.getString("name");
            String navigationId = object.getString("navigationId");

            //     .getJsonObject("address") //key
            //     .getJsonObject("email") //key
            //     .getJsonArray("lineItems") //key
            //     .getJsonObject("name") //key
            //     .getString("navigationId"); //value
                
                System.out.printf("\r\n Address>>> %s\n", address);
                System.out.printf("\r\n Email>>> %s\n", email);
                System.out.printf("\r\n List Items>>> %s\n", listItems);
                System.out.printf("\r\n Name>>> %s\n", name);
                System.out.printf("\r\n Navigation ID>>> %s\n", navigationId);

        return getQuotations(items);

        // RequestEntity<Void> req = RequestEntity
        //     .get(url)
        //     .accept(MediaType.APPLICATION_JSON)
        //     .build();
             
        
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
