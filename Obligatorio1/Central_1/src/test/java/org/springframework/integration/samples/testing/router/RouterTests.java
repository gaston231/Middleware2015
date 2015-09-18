
package org.springframework.integration.samples.testing.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
//import static org.springframework.integration.test.matcher.PayloadMatcher.hasPayload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.samples.ws.RouterConsulta;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * Shows how to test a router; the router is configured to route to direct
 * channel names. The configuration would
 * be a fragment of a larger flow. Since the output channels are direct,
 * they have no subscribers outside the context of a larger flow. So, 
 * in this test case, we bridge them to {@link QueueChannel}s to
 * facilitate easy testing.
 * 
 * @author Gary Russell
 * @since 2.0.2
 */
@ContextConfiguration	// default context name is <ClassName>-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
public class RouterTests {
	
	@Autowired
	MessageChannel inputChannel;
	
	@Autowired
	QueueChannel testVentaEntradaChannel;
	
	@Autowired
	QueueChannel testPagoFacturaChannel;
	
	@Autowired
	QueueChannel testUnknownOperationChannel;
	
	@Test
	public void unitTestClassCat() {
		String payload = "CAT:Fluffy";
		assertEquals("ventaEntradaChannel", new RouterConsulta().route(payload));
	}
	
	@Test
	public void unitTestClassDog() {
		String payload = "DOG:Fido";
		assertEquals("pagoFacturaChannel", new RouterConsulta().route(payload));
	}
	
	@Test
	public void unitTestClassLizard() {
		String payload = "LIZARD:Scaly";
		assertEquals("unknownOperationchannel", new RouterConsulta().route(payload));
	}
	
	@Test
	public void testCat() {
		String payload = "CAT:Fluffy";
		Message<String> message = MessageBuilder.withPayload(payload).build();
		inputChannel.send(message);
		Message<?> outMessage = testVentaEntradaChannel.receive(0);
		assertNotNull("Expected an output message", outMessage);
//		assertThat(outMessage, hasPayload(payload));
	}

	@Test
	public void testDog() {
		String payload = "DOG:Fido";
		Message<String> message = MessageBuilder.withPayload(payload).build();
		inputChannel.send(message);
		Message<?> outMessage = testPagoFacturaChannel.receive(0);
		assertNotNull("Expected an output message", outMessage);
//		assertThat(outMessage, hasPayload(payload));
	}

	@Test
	public void testLizard() {
		String payload = "LIZARD:Scaly";
		Message<String> message = MessageBuilder.withPayload(payload).build();
		inputChannel.send(message);
		Message<?> outMessage = testUnknownOperationChannel.receive(0);
		assertNotNull("Expected an output message", outMessage);
//		assertThat(outMessage, hasPayload(payload));
	}
}
