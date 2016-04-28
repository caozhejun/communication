package com.caozj.test;

import java.io.IOException;
import java.util.List;

import com.zhxjz.framework.util.socket.SocketClientUtil;
import com.zhxjz.framework.util.socket.SocketServerUtil;

public class TestSocket {

	public static void main(String[] args) throws IOException {
		final int port = 9876;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SocketServerUtil.start(port);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		final int threadCount = 10;
		final int count = 10;
		final long now = System.currentTimeMillis();
		for (int t = 0; t < threadCount; t++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < count; i++) {
						// SocketClientUtil.sendAndGetReply("127.0.0.1", port,
						// "dasf");
						SocketClientUtil.sendContent("127.0.0.1", port, "dasf");
						System.out.println("发送socket请求" + i + "次,共耗时:" + (System.currentTimeMillis() - now));
					}

				}
			}).start();
		}
//		List<String> result = SocketServerUtil.getResp(port);
//		for (String content : result) {
//			System.out.println("server receive:" + content);
//		}
//		SocketServerUtil.removeAll(port);
//		SocketServerUtil.stop(port);
		
	}
}
