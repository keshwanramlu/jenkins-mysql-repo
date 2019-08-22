package com.example.jenkinsmysqldemo;

import com.example.jenkinsmysqldemo.dao.GreetingDao;
import com.example.jenkinsmysqldemo.domain.Greeting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JenkinsMysqlDemoApplicationTests {

	@Autowired
	public GreetingDao greetingDao;

	@Before
	public  void setup(){
		List<Greeting> greetingList = greetingDao.getAll();
		greetingList.stream().forEach(g -> greetingDao.deleteGreetingById(g.getId()));
	}

	@After
	public void tearDown(){
		List<Greeting> greetingList = greetingDao.getAll();
		greetingList.stream().forEach(g -> greetingDao.deleteGreetingById(g.getId()));

	}

	@Test
	public void testCreateGreeting() {
		Greeting greeting = new Greeting();
		greeting.setGreeting("Hi");

		Greeting result = greetingDao.createGreeting(greeting);

		assertTrue(greeting.equals(result));
	}

	@Test
	public void testGetGreetingById(){
		Greeting greeting = new Greeting();
		greeting.setGreeting("Hi");

		Greeting created = greetingDao.createGreeting(greeting);

		Greeting result = greetingDao.getGreetingById(created.getId());
		greeting.setId(created.getId());
		assertTrue(greeting.equals(result));

	}

}
