package com.p2p.p4f.server;

import com.p2p.p4f.protocols.ClientMessage;
import com.p2p.p4f.protocols.InfoResponse;
import com.p2p.p4f.protocols.ServerMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
			InfoResponse info = dbHandler.Login(clientMsg.getAccount());
			response.setOpcode(op);
			response.setInfoResponse(info);
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
