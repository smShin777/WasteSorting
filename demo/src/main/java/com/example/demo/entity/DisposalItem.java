package com.example.demo.entity;

public class DisposalItem {
    private String itemId;
    private String itemName;
    private String category;

    public DisposalItem(String itemId, String itemName, String category)
    {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
    }

    public String getItemId()
    {
        return itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getCategory()
    {
        return category;
    }
}
