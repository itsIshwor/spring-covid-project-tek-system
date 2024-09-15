package com.hackerrank.eval;

import com.hackerrank.eval.extensions.RESTExtension;
import static io.restassured.RestAssured.get;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({RESTExtension.class})
class BestPracticeTests {

  @Test
  @DisplayName("statusCode404WhenNonExistentCovidRequested")
  void statusCode404WhenNonExistentCovidRequested() throws Exception {
    get("/covid/-1")
            .then().statusCode(SC_NOT_FOUND);
  }


  @Test
  @DisplayName("statusCode400WhenTop5ByInvalid")
  void statusCode400WhenTop5ByInvalid() throws Exception {
    get("/covid/top5?by=invalid")
            .then().statusCode(SC_BAD_REQUEST);
  }

  @Test
  @DisplayName("statusCode400WhenTotalByInvalid")
  void statusCode400WhenTotalByInvalid() throws Exception {
    get("/covid/total?by=invalid")
            .then().statusCode(SC_BAD_REQUEST);
  }
}
