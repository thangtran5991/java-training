/*
 * LogInfo
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

public class LogInfo {
    private int adId;
    private int siteId;
    private int logType;
    private int cost;
    private int wholesale;

    public LogInfo(int adId, int siteId, int logType, int cost, int wholesale) {
        this.adId = adId;
        this.siteId = siteId;
        this.logType = logType;
        this.cost = cost;
        this.wholesale = wholesale;
    }

    public int getAdId() {
        return adId;
    }

    public int getSiteId() {
        return siteId;
    }

    public int getCost() {
        return cost;
    }

    public int getWholesale() {
        return wholesale;
    }
}
