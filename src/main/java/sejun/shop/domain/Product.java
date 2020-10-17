package sejun.shop.domain;

public class Product {
    private String name;
    private String price;
    private String explain;
    private String option;
    private String sort;
    private String detailUrl;
    private String url;
    private int score;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /*
    * create table product(
    name varchar2 (100),
    price varchar2 (20),
    option varchar2 (100),
    sort varchar2 (100),
    detailUrl varchar2 (100),
    url varchar2 (100)
);
    * */
}
