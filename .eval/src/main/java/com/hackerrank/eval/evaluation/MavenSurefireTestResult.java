package com.hackerrank.eval.evaluation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "testsuite")
public class MavenSurefireTestResult {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "testcase")
  private List<MavenSurefireSingleTestResult> testCases;
}
