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

public class ListEmpServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rst = null;

		try {
			conn = DBUtils.getConnection();
			prep = conn.prepareStatement("select * from t_emp");
			rst = prep.executeQuery();
			pw.write("<table border='1 solid red' cellpadding='5' cellspacing='5'>");
			pw.write("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td><td>操作</td></tr>");
			
			while (rst.next()) {
				String id = String.valueOf(rst.getInt("t_id"));
				String name = rst.getString("empname");
				Double salary = rst.getDouble("empsalary");
				int age = rst.getInt("empage");
				pw.write("<tr><td>" + id + "</td><td>" + name + "</td><td>"
						+ salary + "</td><td>" + age + "</td><td>"
						+ "<a href='del?id=" + id + "'>删除员工</a> "
						+ " <a href='load?id="+id+"'>修改员工</a>"+"</td></tr>");
			} 
			pw.write("</table>");
			pw.write("<a href='addEmp.html'>添加员工</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rst!=null){
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prep!=null){
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
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
