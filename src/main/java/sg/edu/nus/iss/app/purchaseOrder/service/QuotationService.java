package sg.edu.nus.iss.app.purchaseOrder.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.app.purchaseOrder.model.Quotation;

@Service
public class QuotationService {
    Logger logger = LoggerFactory.getLogger(QuotationService.class);

 
    // public ArrayList<String> getItemByName(String quote) {
          
        final String url = "https://quotation.chuklee.com/" + "/order";
        
    public Optional<Quotation> getQuotations(List<String> items) {
            
            JsonArrayBuilder array = Json.createArrayBuilder();

            //do a for each loop to say 
            //For each "string" item in the array
            //items to get quotation for..
            for (String item : items) {
                array.add(item);
            }

            RequestEntity<String> req = RequestEntity
                    .post(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    // .headers(“Accept”, MediaType.APPLICATION_JSON)
                    .body(array.toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject object = reader.readObject();


        return getQuotations(items);

        // RequestEntity<Void> req = RequestEntity
        //     .get(url)
        //     .accept(MediaType.APPLICATION_JSON)
        //     .build();
             
        
        
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO: handle exception
        }
        return null;
    }
}
