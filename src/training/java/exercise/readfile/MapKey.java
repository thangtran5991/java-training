package training.java.exercise.readfile;

import java.util.Objects;

public class MapKey {
    private int key1;
    private int key2;

    public MapKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getKey1() {
        return key1;
    }

    public void setKey1(int key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapKey mapKey = (MapKey) o;
        return key1 == mapKey.key1 &&
                key2 == mapKey.key2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key1, key2);
    }
}
