package util;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import model.User;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

    private ArrayList<User> arrayList = new ArrayList();

    public void getAllUsers() {
        RestAssured.baseURI = Constants.baseUrl;
        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request
                .when()
                .get(Constants.listUsers)

                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = response.getStatusCode();

        if (responseCode == 200) {

            ResponseBody responseBody = response.getBody();

            for (Object u : responseBody.jsonPath().getList("data")
            ) {
                HashMap<String, String> users = (HashMap<String, String>) u;
                String userID = users.get("id");
                String userName = users.get("first_name");
                String userMail = users.get("mail");
                String userSurName = users.get("last_name");
                String avatar = users.get("avatar");
                User user = new User(userID, userMail, userName, userSurName, avatar);
                arrayList.add(user);

            }
            assert responseBody.asString() != null;
        }
    }


    public void getSingleUser() {
        RestAssured.baseURI = Constants.baseUrl;
        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request
                .when()
                .get(Constants.listUserSingle)
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = response.getStatusCode();

        if (responseCode == 200) {

            ResponseBody responseBody = response.getBody();
            System.out.println(responseBody.jsonPath().getString("data"));

            assert responseBody.asString() != null;
        }

    }


    public void getAllDelay() {
        RestAssured.baseURI = Constants.baseUrl;
        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request
                .when()
                .get(Constants.listDelayedResponse)

                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = response.getStatusCode();

        if (responseCode == 200) {

            ResponseBody responseBody = response.getBody();

            for (Object u : responseBody.jsonPath().getList("data")
            ) {
                System.out.println(u.toString());


            }
            assert responseBody.asString() != null;
        }

    }

    public void getNotFound() {
        RestAssured.baseURI = Constants.baseUrl;
        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request
                .when()
                .get(Constants.singleNotFound)
                .then()
                .statusCode(404)
                .extract().response();

        int responseCode = response.getStatusCode();

        if (responseCode == 404) {

            ResponseBody responseBody = response.getBody();
            System.out.println("404 Not Found");

            assert responseBody.asString() == null;
        }

    }

    //login user email and password with post method
    public void loginUser(String email, String password) {

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", email);
        requestParams.put("password", password);

        RestAssured.baseURI = Constants.baseUrl;
        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request.body(requestParams.toString())
                .when()
                .post(Constants.loginUser)
                .then()
                .extract().response();

        int responseCode = response.getStatusCode();

        if (responseCode == 200) {

            ResponseBody responseBody = response.getBody();
            System.out.println(responseBody.toString());

            assert responseBody.asString() == null;
        }

    }


}
