package sg.edu.nus.iss.app.purchaseOrder.model;

public class OrderModel {
    private String name;
    private String address;
    private String email;
    private String invoiceId;

    public OrderModel(String name, String address, String email, String invoiceId) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.invoiceId = invoiceId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    


}
