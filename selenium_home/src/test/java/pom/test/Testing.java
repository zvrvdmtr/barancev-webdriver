package pom.test;

import org.junit.Test;

/**
 * Created by Zhanna on 26.03.2017.
 */
public class Testing extends Base {
    @Test
    public void test() throws Exception{
        app.open();
        for (int i = 0; i < 3; i++){
            app.addGood();
        }
        app.checkout();
        app.deleteFew();
    }
}
