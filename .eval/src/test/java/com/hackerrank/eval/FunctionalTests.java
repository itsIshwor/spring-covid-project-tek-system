package com.hackerrank.eval;

import com.hackerrank.eval.extensions.RESTExtension;
import com.hackerrank.eval.model.Covid;
import static io.restassured.RestAssured.get;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;
import java.util.List;

@ExtendWith({RESTExtension.class})
class FunctionalTests {

  @Test
  @DisplayName("test top 5")
  void testTop5() {
    List<Covid> actual = Arrays.asList(get("/covid/top5?by=death")
            .then()
            .statusCode(SC_OK)
            .extract()
            .response()
            .as(Covid[].class));

    assertThat(
            actual.stream().map(Covid::getCountry).toArray(),
            arrayContaining(new String[]{"country4", "country2", "country5", "country7", "country3"}));
  }

  @Test
  @DisplayName("total")
  void testTotal() {
    Integer actual = get("/covid/total?by=death")
            .then()
            .statusCode(SC_OK)
            .extract()
            .response()
            .as(Integer.class);

    Assertions.assertEquals(28, actual);
  }
}
