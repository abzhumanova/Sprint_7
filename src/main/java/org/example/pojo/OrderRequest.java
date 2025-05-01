package org.example.pojo;

import java.util.List;

public class OrderRequest {

    // обязательные поля
    private String firstName  = "Ivan";
    private String lastName   = "Ivanov";
    private String address    = "Moscow, Tverskaya 1";
    private int    metroStation = 4;
    private String phone      = "+7 999 111 22 33";
    private int    rentTime   = 2;
    private String deliveryDate = "2025-12-12";
    private String comment    = "Autotest order";

    // проверяемые цвета
    private List<String> color;

    public OrderRequest() { }

    public List<String> getColor()          { return color; }
    public void setColor(List<String> color){ this.color = color; }

    /* остальные сеттеры при необходимости */
}