package bean;

/**
 * date:2017/4/8.
 * author:刘宏亮.
 * function:
 */

public class Bean {
    private int image;
    private String name;

    public Bean(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
