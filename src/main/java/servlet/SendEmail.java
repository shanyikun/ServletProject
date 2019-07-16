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
		// �ռ��˵ĵ����ʼ� ID
		String from = "893669092@qq.com";
		final String userName = "893669092";

		// �ĵ����ʼ� ID
		String to = "yikun_shan@163.com";

		// ��������������
		final String password = "syk893669092";

		// �������Ǵӱ����������͵����ʼ�
		String host = "smtp.qq.com";

		// ��ȡϵͳ������
		Properties properties = System.getProperties();

		// �����ʼ�������
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");

		// ��ȡĬ�ϵ� Session ����
		 Session session = Session.getInstance(properties,new Authenticator() {
             protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                 return new javax.mail.PasswordAuthentication(userName,
                		 password);
             }
         });
		 

		/* 
         MimeMessage message = new MimeMessage(session);// �õ�MimeMessage��ʵ��
         message.setFrom( new InternetAddress(from));// ���÷�����
         
         message.addRecipients(Message.RecipientType. TO,InternetAddress.parse(to)); */


		// ������Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// ����һ��Ĭ�ϵ� MimeMessage ����
			MimeMessage message = new MimeMessage(session);
			// ���� From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// ���� To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// ���� Subject: header field
			message.setSubject("This is the Subject Line!");
			// ��������ʵ����Ϣ
			message.setText("This is actual message");
			// ������Ϣ
			Transport transport = session.getTransport("smtp" );// �õ�Transport���һ��ʵ��
            transport.connect( "smtp.qq.com",userName,password); // ���ӷ��ŷ�����
            transport.sendMessage(message, message.getAllRecipients());// �����ʼ�
//			Transport.send(message);
			String title = "���͵����ʼ�";
			String res = "�ɹ�������Ϣ...";
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
