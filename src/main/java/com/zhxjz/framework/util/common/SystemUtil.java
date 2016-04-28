package com.zhxjz.framework.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统工具类
 * 
 * @author caozj
 *
 */
public class SystemUtil {

	private static final Log logger = LogFactory.getLog(SystemUtil.class);

	/**
	 * 获取当前进程的pid
	 * 
	 * @return
	 */
	public static String getProcessID() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		return name.split("@")[0];
	}

	/**
	 * 获取当前机器的名称
	 * 
	 * @return
	 */
	public static String getMachineName() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		return name.split("@")[1];
	}

	/**
	 * 判断进程是否运行
	 * 
	 * @param exeName
	 *            进程名
	 * @return
	 */
	public static boolean isRunning(String exeName) {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("tasklist");
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String info = br.readLine();
			while (info != null) {
				if (info.indexOf(exeName) >= 0) {
					return true;
				}
				info = br.readLine();
			}
		} catch (IOException e) {
			logger.error("获取进程列表失败", e);
		}
		return false;
	}

	/**
	 * 获取当前机器ip地址
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIpAddress() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		return address.getHostAddress();
	}

	/**
	 * Java 运行时环境版本
	 * 
	 * @return
	 */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	/**
	 * 获取Java 安装目录
	 * 
	 * @return
	 */
	public static String getJavaHome() {
		return System.getProperty("java.home");
	}

	/**
	 * Java 类路径
	 * 
	 * @return
	 */
	public static String getJavaClassPath() {
		return System.getProperty("java.class.path");
	}

	/**
	 * 加载库时搜索的路径列表
	 * 
	 * @return
	 */
	public static String getSystemEnvPath() {
		return System.getProperty("java.library.path");
	}

	/**
	 * 默认的临时文件路径
	 * 
	 * @return
	 */
	public static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 操作系统的名称
	 * 
	 * @return
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * 操作系统的架构
	 * 
	 * @return
	 */
	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	/**
	 * 操作系统的版本
	 * 
	 * @return
	 */
	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * 用户的账户名称
	 * 
	 * @return
	 */
	public static String getUserName() {
		return System.getProperty("user.name");
	}

	/**
	 * 用户的主目录
	 * 
	 * @return
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	/**
	 * 用户的当前工作目录
	 * 
	 * @return
	 */
	public static String getUserDir() {
		return System.getProperty("user.dir");
	}

	public static void main(String[] args) throws UnknownHostException, SocketException {
	}

}
