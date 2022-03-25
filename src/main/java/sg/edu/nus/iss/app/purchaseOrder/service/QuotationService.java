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
        
            Quotation quot = new Quotation();
            quot.setQuoteId("quoteId");
            for (int i = 0; i <object. getJsonArray("quotations").size(); i ++) {

                JsonObject obj = object.getJsonArray("quotations").getJsonObject(i);
                quot.addQuotation(obj.get("item").toString(), 
                Float.parseFloat(obj.get("unitPrice").toString()));
            }
          
            logger.info(" object >>> " + object);

            // System.out.println(object.getJsonObject("address").getJsonObject("email")
            //                     .getJsonArray("lineItems").getJsonObject("name").getJsonObject("navigationId"));

            // String address = object.getString("address");
            
            // .getJsonObject("address") //key
                // System.out.printf("\r\n Address>>> %s\n", address);

        return Optional.of(quot);
            
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
