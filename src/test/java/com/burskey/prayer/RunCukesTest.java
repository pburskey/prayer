package com.burskey.prayer;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false ,
        plugin = {"pretty"}
)
public class RunCukesTest {
}
