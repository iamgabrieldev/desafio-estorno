package gabriel.desafio_estorno.dto;

public class RefundRequest {
    private String transactionId;
    private double amount;
    private String reason;

    // Getters e Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "RefundRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                '}';
    }
}
