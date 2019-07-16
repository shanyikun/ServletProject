package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmail
 */
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 收件人的电子邮件 ID
		String from = "893669092@qq.com";
		final String userName = "893669092";

		// 的电子邮件 ID
		String to = "yikun_shan@163.com";

		// 发件人邮箱密码
		final String password = "syk893669092";

		// 假设您是从本地主机发送电子邮件
		String host = "smtp.qq.com";

		// 获取系统的属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");

		// 获取默认的 Session 对象
		 Session session = Session.getInstance(properties,new Authenticator() {
             protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                 return new javax.mail.PasswordAuthentication(userName,
                		 password);
             }
         });
		 

		/* 
         MimeMessage message = new MimeMessage(session);// 得到MimeMessage的实例
         message.setFrom( new InternetAddress(from));// 设置发件人
         
         message.addRecipients(Message.RecipientType. TO,InternetAddress.parse(to)); */


		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// 创建一个默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// 设置 From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// 设置 To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 设置 Subject: header field
			message.setSubject("This is the Subject Line!");
			// 现在设置实际消息
			message.setText("This is actual message");
			// 发送消息
			Transport transport = session.getTransport("smtp" );// 得到Transport类的一个实例
            transport.connect( "smtp.qq.com",userName,password); // 连接发信服务器
            transport.sendMessage(message, message.getAllRecipients());// 发送邮件
//			Transport.send(message);
			String title = "发送电子邮件";
			String res = "成功发送消息...";
			String docType = "<!DOCTYPE html> \n";
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
					+ "<p align=\"center\">" + res + "</p>\n" + "</body></html>");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
