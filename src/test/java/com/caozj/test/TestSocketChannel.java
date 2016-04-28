package com.caozj.test;

import java.io.IOException;

import com.zhxjz.framework.util.socket.SocketChannelClientUtil;
import com.zhxjz.framework.util.socket.SocketChannelServerUtil;

public class TestSocketChannel {

	public static void main(String[] args) throws IOException {
		final int port = 9876;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SocketChannelServerUtil.start(port);
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
		final int threadCount = 100;
		final int count = 100;
		final long now = System.currentTimeMillis();
		for (int t = 0; t < threadCount; t++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < count; i++) {
						byte[] response = null;
						try {
							response = SocketChannelClientUtil.send("127.0.0.1", port, new String("client send data ").getBytes());
						} catch (IOException e) {
						}
						if (response != null) {
							System.out.println("response:" + new String(response));
						}
						// List<byte[]> result =
						// SocketChannelServerUtil.getResp(port);
						// for (byte[] content : result) {
						// System.out.println("server receive:" + new
						// String(content));
						// }
//						SocketChannelServerUtil.removeAll(port);
						System.out.println("发送socket请求" + i + "次,共耗时:" + (System.currentTimeMillis() - now));
					}
				}
			}).start();
		}
		// SocketChannelServerUtil.stop(port);

	}
}
