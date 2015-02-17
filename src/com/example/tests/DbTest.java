package com.example.tests;

import org.testng.annotations.Test;

public class DbTest extends TestBase{

  @Test
  public void testDBConnection() throws Exception {
	 System.out.println(app.getHibernateHelper().getGroups());
  } 
}
