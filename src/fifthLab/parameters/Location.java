package fifthLab.parameters;

public class Location {
    private Long x; //Поле не может быть null
    private long y;
    private Integer z; //Поле не может быть null

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
