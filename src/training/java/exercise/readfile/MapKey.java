package training.java.exercise.readfile;

import java.util.Objects;

public class MapKey {
    private int adId;
    private int siteId;

    public MapKey(int adId, int siteId) {
        this.adId = adId;
        this.siteId = siteId;
    }

    public int getAdId() {
        return adId;
    }

    public int getSiteId() {
        return siteId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MapKey mapKey = (MapKey) obj;
        return adId == mapKey.adId &&
                siteId == mapKey.siteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, siteId);
    }
}
