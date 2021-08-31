package com.p2p.p4f.server;

import com.p2p.p4f.protocols.ClientMessage;
import com.p2p.p4f.protocols.InfoResponse;
import com.p2p.p4f.protocols.RegisterInfo;
import com.p2p.p4f.protocols.ServerMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageHandler extends ChannelInboundHandlerAdapter {
	private String username = null;
	private int type = 0;
	private Logger logger = LogManager.getLogger(ServerP4F.class.getName());
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		logger.info("A client from " + ctx.channel().remoteAddress().toString() + " has connected.");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		logger.info("A client from " + ctx.channel().remoteAddress().toString() + " has disconnected.");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
		ClientMessage clientMsg = (ClientMessage) msg;
		int op = clientMsg.getOpcode();
		DBHandler dbHandler = new DBHandler();
		ServerMessage.Builder response = ServerMessage.newBuilder();
		if (op == 1) {
			logger.info(username + " has logged in system.");
			InfoResponse info = dbHandler.Login(clientMsg.getAccount());
			if (info.getReCode() == 0 || info.getReCode() == 3) {
				logger.info(username + "logged in ok");
				username = ((ClientMessage) msg).getAccount().getUsername();
				type = (info.getReCode() == 0)? 1 : 0;
			}
			else if (info.getReCode() == 1) {
				logger.info(username + "logged in failed. Error: username fault");
			}
			else if (info.getReCode() == 2) {
				logger.info(username + "logged in failed. Error: pass fault");
			}
			response.setOpcode(op);
			response.setInfoResponse(info);
		}
		if (op == 2) {
			// 0 = no fault, 1 = user fault, 2 = email fault, 3 = phone fault
			int result = dbHandler.Register(clientMsg.getRegAcc());
			switch (result) {
				case 0:
					logger.info(username + " register ok");
					break;
				case 1:
					logger.info(username + " user fault");
					break;
				case 2:
					logger.info(username + " email ok");
					break;
				case 3:
					logger.info(username + " phone fault");
					break;
			}
			response.setOpcode(op);
			response.setResponseCode(result);
		}
		if (op == 3) {
			int result = dbHandler.ChangePassword(clientMsg.getChangeRes());
			switch (result) {
				case 1:
					logger.info(username + " changed password ok");
					break;
				case -1:
					logger.info(username + " changed password failed");
					break;
			}
			response.setOpcode(op);
			response.setResponseCode(result);
		}
		dbHandler.releaseConn();
		ctx.writeAndFlush(response.build());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		logger.error("An exception occured at client " + ctx.channel().remoteAddress().toString(), cause);
	}
}
