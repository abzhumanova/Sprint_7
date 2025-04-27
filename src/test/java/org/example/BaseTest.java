package org.example;

import org.example.steps.CourierSteps;
import org.example.steps.OrderSteps;
import org.example.utils.TestDataGenerator;
import org.junit.Before;

public abstract class BaseTest {
    protected CourierSteps courierSteps = new CourierSteps();
    protected OrderSteps orderSteps = new OrderSteps();

    protected String login;
    protected String password;
    protected String firstName;

    @Before
    public void initTestData() {
        login = TestDataGenerator.randomLogin();
        password = TestDataGenerator.randomPassword();
        firstName = TestDataGenerator.randomName();
    }
}