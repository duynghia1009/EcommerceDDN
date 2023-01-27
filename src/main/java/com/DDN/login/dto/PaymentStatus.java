package com.DDN.login.dto;

public class PaymentStatus {

    boolean payment_failed;
    Long order_id;
    String charge_id;
    String txn_id;
    String receipt_url;

    public PaymentStatus() {}

    public PaymentStatus(Long order_id, boolean payment_failed, String charge_id, String txn_id, String receipt_url) {
        this.order_id = order_id;
        this.payment_failed = payment_failed;
        this.charge_id = charge_id;
        this.txn_id = txn_id;
        this.receipt_url = receipt_url;
    }

    public boolean isPayment_failed() {
        return payment_failed;
    }

    public void setPayment_failed(boolean payment_failed) {
        this.payment_failed = payment_failed;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(String charge_id) {
        this.charge_id = charge_id;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getReceipt_url() {
        return receipt_url;
    }

    public void setReceipt_url(String receipt_url) {
        this.receipt_url = receipt_url;
    }
}
