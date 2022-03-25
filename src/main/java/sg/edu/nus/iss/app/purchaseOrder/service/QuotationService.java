package sg.edu.nus.iss.app.purchaseOrder.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.slf4j.*;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {
    Logger logger = LoggerFactory.getLogger(QuotationService.class);

    public ArrayList<String> getItemByName(String itemName) throws IOException {
          
        String url = "/item/" + itemName;
        
        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        

        RestTemplate template = new RestTemplate();
        ///String globalImg="test";
        ResponseEntity<String> resp = template.exchange(req, String.class);
        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject object = reader.readObject();
        
        
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO: handle exception
        }
        return null;
    }
}
