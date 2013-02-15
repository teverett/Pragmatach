<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
	<appender name="CONSOLE" class="org.apache.log4j.ConsoleAppender">
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%p - %C{1}.%M(%L) | %m%n" />
		</layout>
	</appender>
	<appender name="FILE" class="org.apache.log4j.FileAppender">
		<param name="file" value="${name}.log" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%p - %C{1}.%M(%L) | %m%n" />
		</layout>
	</appender>
	<logger name="com.khubla.com.pragmatach.examples.rssviewer">
		<level value="ALL" />
	</logger>
	<logger name="com.khubla.pragmatach">
		<level value="ALL" />
	</logger>
	<logger name=" org.apache.commons">
		<level value="FATAL" />
	</logger>
	<root>
		<level value="ALL" />
		<appender-ref ref="CONSOLE" />
		<appender-ref ref="FILE" />
	</root>
</log4j:configuration>
