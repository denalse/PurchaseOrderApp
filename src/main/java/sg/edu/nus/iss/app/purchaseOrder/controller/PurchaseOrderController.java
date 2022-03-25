// package sg.edu.nus.iss.app.purchaseOrder.controller;

// import java.util.ArrayList;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestParam;

// import sg.edu.nus.iss.app.purchaseOrder.service.QuotationService;

// @Controller
// public class PurchaseOrderController {
//     private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

//     @Autowired
//     private QuotationService service;

//     @GetMapping
//     public String showPurchaseOrder(){
//         return "index";
//     }
//     @GetMapping("/item")
//     public String Item(
//         @RequestParam (name="item_name") String itemName, Model model) {
    
//         ArrayList<String> result = service.getItemByName(itemName);
//         logger.info("\r\n Result >>> " + result);

//         model.addAttribute("apple", result);
//         model.addAttribute("durian", result);
//         model.addAttribute("grapes", result);
//         model.addAttribute("orange", result);
//         model.addAttribute("pear", result);


//         return "orderResult";

//         }
// }
