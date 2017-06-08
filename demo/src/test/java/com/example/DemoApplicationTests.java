package com.example;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.PropertySourcesPropertyValues;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {
    
    @Autowired
	private MockMvc mockMvc;

	@Autowired
	ConfigurableEnvironment environment;

	@Autowired
	ConfigurableWebApplicationContext context;

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void contextLoads() {
		PropertyValue propertyValue = new PropertySourcesPropertyValues(
				this.environment.getPropertySources())
						.getPropertyValue("spring.application.name");
	}

	@Test
	public void resource() throws Exception {
		this.mockMvc.perform(get("/greeting"))
				.andExpect(content().string(containsString("Hello World!")));
	}

}
