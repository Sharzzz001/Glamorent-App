package sharan.cool.lasttryglam;

public class cartmodel
{
    String pname, purl, pprice;

    public cartmodel()
    {

    }

    public cartmodel(String pname, String purl, String pprice) {
        this.pname = pname;
        this.purl = purl;
        this.pprice = pprice;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getPurl()
    {
        return purl;
    }

    public void setPurl(String purl)
    {
        this.purl = purl;
    }

    public String getPprice()
    {
        return pprice;
    }

    public void setPprice(String pprice)
    {
        this.pprice = pprice;
    }

}
