package sharan.cool.lasttryglam;

public class model {
    String iurl;
    String name;
    String price;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    model()
    {

    }

    public model(String iurl, String name, String price) {
        this.iurl = iurl;
        this.name = name;
        this.price = price;

    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) {
        this.price = price;
    }




}
