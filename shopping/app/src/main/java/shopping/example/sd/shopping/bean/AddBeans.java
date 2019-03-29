package shopping.example.sd.shopping.bean;

public class AddBeans {
    private String commodityId;
    private int count;


    public AddBeans(String commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
