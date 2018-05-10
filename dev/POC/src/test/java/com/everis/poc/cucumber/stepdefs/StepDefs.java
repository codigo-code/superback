package com.everis.poc.cucumber.stepdefs;

import com.everis.poc.PocApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = PocApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
