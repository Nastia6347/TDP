package com.example.fw;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DataBaseHelper extends HelperBase {
	
	private java.sql.Connection conn; //ссылка на бд
	
	public DataBaseHelper (ApplicationManager manager) {
		super(manager);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //создание нового объекта типа com.mysql.jdbc.Driver
		} catch (Exception e) {
			e.printStackTrace();//перехват исключений
		}
		try {
			conn = DriverManager.getConnection(manager.getProperty("db.url")); //установление соединение с бд по ссылки url
		} catch (SQLException e) {
			e.printStackTrace();//перехват исключений
		}
	}
	 //получение последнего id присвоеного группе
	public String getLastGroupId() {		
		Statement st = null;// создаем объект запрос
		ResultSet res = null;
		try {
			st = conn.createStatement();//инициализируем запрос
			res = st.executeQuery("SELECT MAX(group_id) FROM group_list");//запрос к таблице roup list
			if (res.next()) {
				return res.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //финализаци€
			try {
				//закрываем соединени€
				res.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();//перехват исключений
			}
		}
	}
	 //получение списка групп
	public Set<GroupObject> getGroups() {
		Set<GroupObject> groups = new HashSet<GroupObject>();
		Statement st = null;//запрос
		ResultSet res = null;
		try {
			st = conn.createStatement();//инициализируем запрос
			res = st.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list");//запрос к таблице roup list
			while (res.next()) {
				//извлекаем построчно €чейки
				groups.add(new GroupObject().setId(res.getString(1)).setName(res.getString(2)).setHeader(res.getString(3)).setFooter(res.getString(4)));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //финализаци€
			try {
				//закрываем соединени€
				res.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();//перехват исключений
			}
		}
		return groups;
	}

}
