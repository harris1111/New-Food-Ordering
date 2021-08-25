import com.p2p.p4f.protocols.ClientMessage;
import com.p2p.p4f.protocols.LoginInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProtobufEncoderDecoderTest {
	@Test
	public void testLoginRequest() {
		try {
			ClientMessage loginReq = ClientMessage.newBuilder()
					.setOpcode(1)
					.setAccount(LoginInfo.newBuilder()
					.setUsername("ak123")
					.setPassword("12345")).build();
			
			EmbeddedChannel eChan = new EmbeddedChannel();
			ChannelPipeline pipeline = eChan.pipeline();
			pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
			pipeline.addLast(new ProtobufEncoder());
			pipeline.addLast(new ProtobufVarint32FrameDecoder());
			pipeline.addLast(new ProtobufDecoder(ClientMessage.getDefaultInstance()));
			
			eChan.writeOutbound(loginReq);
			ByteBuf buf = (ByteBuf) eChan.readOutbound();
			
			eChan.writeInbound(buf);
			ClientMessage recv = eChan.readInbound();
			
			Assertions.assertEquals(recv.getOpcode(), loginReq.getOpcode());
			Assertions.assertEquals(recv.getAccount(), loginReq.getAccount());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
