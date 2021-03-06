package com.example.fw;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DataBaseHelper extends HelperBase {
	
	private java.sql.Connection conn; //������ �� ��
	
	public DataBaseHelper (ApplicationManager manager) {
		super(manager);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //�������� ������ ������� ���� com.mysql.jdbc.Driver
		} catch (Exception e) {
			e.printStackTrace();//�������� ����������
		}
		try {
			conn = DriverManager.getConnection(manager.getProperty("db.url")); //������������ ���������� � �� �� ������ url
		} catch (SQLException e) {
			e.printStackTrace();//�������� ����������
		}
	}
	 //��������� ���������� id ����������� ������
	public String getLastGroupId() {		
		Statement st = null;// ������� ������ ������
		ResultSet res = null;
		try {
			st = conn.createStatement();//�������������� ������
			res = st.executeQuery("SELECT MAX(group_id) FROM group_list");//������ � ������� roup list
			if (res.next()) {
				return res.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //�����������
			try {
				//��������� ����������
				res.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();//�������� ����������
			}
		}
	}
	 //��������� ������ �����
	public Set<GroupObject> getGroups() {
		Set<GroupObject> groups = new HashSet<GroupObject>();
		Statement st = null;//������
		ResultSet res = null;
		try {
			st = conn.createStatement();//�������������� ������
			res = st.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list");//������ � ������� roup list
			while (res.next()) {
				//��������� ��������� ������
				groups.add(new GroupObject().setId(res.getString(1)).setName(res.getString(2)).setHeader(res.getString(3)).setFooter(res.getString(4)));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //�����������
			try {
				//��������� ����������
				res.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();//�������� ����������
			}
		}
		return groups;
	}

}
