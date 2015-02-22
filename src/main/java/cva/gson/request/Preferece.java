package cva.gson.request;

/**
 * Created by ko on 2015-02-12.
 */
public class Preferece {
    String factor;
    String logical;
    String degree;
    String unit;
    public Preferece(String factor,String logical,String degree,String unit)
    {
        this.factor = factor;
        this.degree = degree;
        this.logical = logical;
        this.unit = unit;
    }

    public String getDegree() {
        return degree;
    }

    public String getFactor() {
        return factor;
    }

    public String getLogical() {
        return logical;
    }

    public String getUnit() {
        return unit;
    }
}
