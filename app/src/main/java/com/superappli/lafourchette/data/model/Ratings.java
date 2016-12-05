package com.superappli.lafourchette.data.model;

public class Ratings
{
    private String ambience_rate;

    private String service_rate;

    private String noice_rate;

    private String global_rate;

    private String price_rate;

    private String waiting_rate;

    private String food_rate;

    public String getAmbience_rate ()
    {
        return ambience_rate;
    }

    public void setAmbience_rate (String ambience_rate)
    {
        this.ambience_rate = ambience_rate;
    }

    public String getService_rate ()
    {
        return service_rate;
    }

    public void setService_rate (String service_rate)
    {
        this.service_rate = service_rate;
    }

    public String getNoice_rate ()
    {
        return noice_rate;
    }

    public void setNoice_rate (String noice_rate)
    {
        this.noice_rate = noice_rate;
    }

    public String getGlobal_rate ()
    {
        return global_rate;
    }

    public void setGlobal_rate (String global_rate)
    {
        this.global_rate = global_rate;
    }

    public String getPrice_rate ()
    {
        return price_rate;
    }

    public void setPrice_rate (String price_rate)
    {
        this.price_rate = price_rate;
    }

    public String getWaiting_rate ()
    {
        return waiting_rate;
    }

    public void setWaiting_rate (String waiting_rate)
    {
        this.waiting_rate = waiting_rate;
    }

    public String getFood_rate ()
    {
        return food_rate;
    }

    public void setFood_rate (String food_rate)
    {
        this.food_rate = food_rate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ambience_rate = "+ambience_rate+", service_rate = "+service_rate+", noice_rate = "+noice_rate+", global_rate = "+global_rate+", price_rate = "+price_rate+", waiting_rate = "+waiting_rate+", food_rate = "+food_rate+"]";
    }
}