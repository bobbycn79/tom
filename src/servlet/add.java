package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;

public class add extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String name = (String) request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		double salary = Double.parseDouble(request.getParameter("salary"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Connection conn = null;
		PreparedStatement prep = null;
		String sql = "insert into t_emp(empname,empage,empsalary) values(?,?,?)";

		try {
			conn = DBUtils.getConnection();
			prep = conn.prepareStatement(sql);
			prep.setString(1, name);
			prep.setInt(2, age);
			prep.setDouble(3, salary);
			prep.executeUpdate();
			response.sendRedirect("list");
			System.out.println(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.print("ÃÌº” ß∞‹");
		} finally {

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
