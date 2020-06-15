package com.runner;

import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = {"\\src\\test\\resources\\Features\\"} , plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "steps")
public class TestRunner  {



}