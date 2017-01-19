package com.test.rmi.chat.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.test.rmi.chat.Chat;
import com.test.rmi.chat.ChatMessage;

public class ChatServer extends UnicastRemoteObject implements Chat {
	private static Vector vc = new Vector();

	public ChatServer() throws RemoteException {
	}

	@Override
	public void setClient(ChatMessage cc) throws RemoteException {
		vc.add(cc);
	}

	@Override
	public void setMessage(String msg) throws RemoteException {
		for (int i = 0; i < vc.size(); i++) {
			ChatMessage cc = (ChatMessage) vc.elementAt(i);
			cc.setMessage(msg);
		}
	}

	public static void main(String[] args) throws Exception {
		ChatServer obj = new ChatServer();
		Naming.rebind("chat",obj);
		System.out.println("Server Ready");
	}
}
