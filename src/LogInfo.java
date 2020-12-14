public class LogInfo {
    private int ad_id;
    private int site_id;
    private int log_type;
    private int cost;
    private int wholesale;
    public LogInfo(int ad_id, int site_id, int log_type, int cost, int wholesale) {
        this.ad_id = ad_id;
        this.site_id = site_id;
        this.log_type = log_type;
        this.cost = cost;
        this.wholesale = wholesale;
    }
    public int getAd_id() {
        return ad_id;
    }

    public int getSite_id() {
        return site_id;
    }

    public int getCost() {
        return cost;
    }

    public int getWholesale() {
        return wholesale;
    }
}
