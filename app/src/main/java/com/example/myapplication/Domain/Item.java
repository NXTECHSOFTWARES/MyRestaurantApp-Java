package com.example.myapplication.Domain;

import androidx.annotation.NonNull;

public class Item {

    private boolean MostFood;
    private boolean OurRecommandationFood;
    private int CategoryId;

    private String Description;
    private int Id;
    private String ImagePath;
    private double Price;
    private String Title;

    public Item() {

    }

    @NonNull
    @Override
    public String toString() {
        return  Title;
    }

    public boolean isMostFood() {
        return MostFood;
    }

    public void setMostFood(boolean mostFood) {
        MostFood = mostFood;
    }

    public boolean isOurRecommandationFood() {
        return OurRecommandationFood;
    }

    public void setOurRecommandationFood(boolean ourRecommandationFood) {
        OurRecommandationFood = ourRecommandationFood;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
