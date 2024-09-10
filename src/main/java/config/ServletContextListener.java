package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	 @Override
	    public void contextInitialized(ServletContextEvent sce) {
	        // 애플리케이션 시작 시 동작할 코드 (필요시 구현)
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	        // 애플리케이션 종료 시 MySQL 드라이버 및 스레드를 정리합니다.
	        try {
	            // JDBC 드라이버 해제
	            Enumeration<Driver> drivers = DriverManager.getDrivers();
	            while (drivers.hasMoreElements()) {
	                Driver driver = drivers.nextElement();
	                DriverManager.deregisterDriver(driver);
	            }
	            // MySQL AbandonedConnectionCleanupThread 종료
	            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
