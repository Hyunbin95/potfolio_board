package kr.hb.board.aop;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//bean을 자동으로 생성하고 AOP 클래스로 만들어주는 어노테이션
@Component
@Aspect
public class LogginAdvice {
	@Around(
		"execution(public * gmail.ggangpae1.springboard..*Dao.*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint)
		throws Throwable{
		//호출하고자 하는 메소드 이름 가져오기
		String methodName = joinPoint.getSignature().toLongString();
		//현재 날짜를 문자열로 만들기
		java.util.Date today = 
			new java.util.Date(System.currentTimeMillis());
		String date = today.toString(); //2019-06-05
		//파일 객체를 생성
		FileOutputStream fos = 
			new FileOutputStream(
				"/Users/503main/Documents/db.log", true);
		PrintWriter pw = new PrintWriter(fos);
		pw.println(methodName + ":" + date);
		pw.flush();
		
		fos.close();
		pw.close();
		
		
		//호출하는 메소드를 실행하는 구문
		Object obj = joinPoint.proceed();
		
		return obj;
	}
}