package com.khubla.pragmatach.plugin.cluster.multicast;

import org.jgroups.*;
import org.jgroups.blocks.cs.*;
import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * The broadcast sender and reciever
 * 
 * @author tome
 */
public class JGroupsSenderReceiver extends ReceiverAdapter {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * the channel
	 */
	private JChannel jChannel;
	/**
	 * cluster name
	 */
	private final static String CLUSTER_NAME = "PragmatachSesion";

	public JGroupsSenderReceiver() {
	}

	/**
	 * message
	 */
	public void receive(Message message) {
		try {
			if (null != message) {
				byte[] buffer = message.getArray();
				if (null != buffer) {
					MulticastMessage.deserialize(message.getArray());
				}
			}
		} catch (final Exception e) {
			logger.error("Exception in receive", e);
		}
	}

	/**
	 * send message
	 */
	public void send(MulticastMessage multicastMessage) throws PragmatachException {
		try {
			// final Message msg = new Message();
			// msg.setArray(MulticastMessage.serialize(multicastMessage));
			// jChannel.send(msg);
			throw new PragmatachException("not implemented");
		} catch (final Exception e) {
			throw new PragmatachException("Exception in send", e);
		}
	}

	public void shutdown() {
		if (null != jChannel) {
			jChannel.close();
			jChannel = null;
		}
	}

	public void startup() throws PragmatachException {
		try {
			/*
			 * the channel
			 */
			// jChannel = new JChannel();
			// jChannel.setReceiver(this);
			// jChannel.connect(CLUSTER_NAME);
			throw new PragmatachException("not implemented");
		} catch (final Exception e) {
			throw new PragmatachException("Exception in startup", e);
		}
	}

	/**
	 * accepted
	 */
	public void viewAccepted(View view) {
	}
}
