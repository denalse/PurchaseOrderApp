package sg.edu.nus.iss.app.purchaseOrder;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseOrderApplicationTests {

	Random ran = new SecureRandom();

	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	void getQuotations() throws Exception {

		double durian = ran.nextDouble(-50, 50);
		double plum = ran.nextDouble(-50, 50);
		double pear = ran.nextDouble(-50, 50);


		//Construct the json payload
		JsonObject reqPayload = Json.createObjectBuilder()
			.add("durian", durian) 
			.add("plum", plum) 
			.add("pear", pear) 
			.add("ops", "plus") 
			.build();

		//Construct http call
		RequestBuilder req = MockMvcRequestBuilders
			.post("/quotation")
			// .header("User-Agent", "junit")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(reqPayload.toString());

			assertThrowsExactly(
				NestedServletException.class, 
					() -> {
						mvc.perform(req);
					});
	}
}
