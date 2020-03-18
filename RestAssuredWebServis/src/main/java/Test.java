import util.Util;

public class Test {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        Util util = new Util();
        util.getAllDelay();
        util.getSingleUser();
        util.getNotFound();
        util.getAllUsers();

        String userName = "eve.holt@reqres.in";
        String password = "cityslicka";

        util.loginUser(userName, password);

    }

}
