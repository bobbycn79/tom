package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;

public class load extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rst = null;
		try {
			conn = DBUtils.getConnection();
			prep = conn.prepareStatement("select * from t_emp where t_id=?");
			prep.setInt(1, id);
			rst = prep.executeQuery();
			pw.write("<table border='1 solid red' cellpadding='5' cellspacing='5'>");
			pw.write("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td><td>");
			while (rst.next()) {
				String id1 = String.valueOf(rst.getInt("t_id"));
				String name = rst.getString("empname");
				Double salary = rst.getDouble("empsalary");
				int age = rst.getInt("empage");
				pw.write("<tr><td>"+id+"</td><td>"+name+"</td><td>"+salary+"</td><td>"+age+"</td><td>");
			}
			pw.write("</table>");
			pw.write("<a>修改信息为：</a>"+"<br/>");
	
			pw.write("<form action='load1?id="+id+"' method='post'>");
			pw.write("姓名：<input type='text' name='name' /><br />");
			pw.write("年龄：<input type='text' name='age' /><br />");
			pw.write("工资：<input type='text' name='salary' /><br />");
			pw.write("<input type='submit' value='确认修改' />");
			pw.write("</form>");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
