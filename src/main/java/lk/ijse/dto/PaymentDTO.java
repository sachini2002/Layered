package lk.ijse.dto;

public class PaymentDTO {
    private String paymentId;
    private String date;
    private String method;
    private String amount;

    private String bookingId;


    public PaymentDTO() {}

    public PaymentDTO(String paymentId, String date, String method, String amount, String bookingId) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.method = method;
        this.amount = amount;
        this.date = date;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", method='" + method + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
