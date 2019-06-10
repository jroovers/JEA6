package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class KweetResourceTest {

    boolean connectionHealth = true;

    public KweetResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        // Check if host is up.
        Assume.assumeTrue(UrlTester.testURL("http://localhost:8080/Backend/rest/kweets", 200));
        // Continue
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/Backend/rest";

    }

    /**
     * Test of getKweetOverview method, of class KweetResource.
     */
    @Test
    public void testGetKweetOverview() {
        System.out.println("getKweetOverview");
        given().when().get("/kweets").then().statusCode(200);
    }

    /**
     * Test of getSingleKweet method, of class KweetResource.
     */
    @Test
    public void testGetSingleKweet() {
        System.out.println("getSingleKweet");
        given().when().get("/kweets/1").then().statusCode(200);

    }

}
