package com.example.demo.entity;

public class DisposalRule {
    private String ruleId;
    private String itemId;
    private String guideline;

    public DisposalRule(String ruleId, String itemId, String guideline)
    {
        this.ruleId = ruleId;
        this.itemId = itemId;
        this.guideline = guideline;
    }

    public String getGuideline()
    {
        return guideline;
    }

    public void setGuideline(String guideline)
    {
        this.guideline = guideline;
    }
}
