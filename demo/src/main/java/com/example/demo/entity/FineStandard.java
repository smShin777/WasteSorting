package com.example.demo.entity;

public class FineStandard {
    private String fineId;
    private String category;
    private int amount;

    public FineStandard(String fineId, String category, int amount)
    {
        this.fineId = fineId;
        this.category = category;
        this.amount = amount;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
