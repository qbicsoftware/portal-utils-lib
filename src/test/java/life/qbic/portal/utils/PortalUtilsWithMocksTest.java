package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class PortalUtilsWithMocksTest {
	
	@PrepareForTest({VaadinService.class, VaadinSession.class, VaadinRequest.class})	
	@Test
	public void testNoLiferayInstance() {
		PowerMockito.mockStatic(VaadinSession.class);
		PowerMockito.mockStatic(VaadinService.class);
		
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final VaadinSession mockSession = Mockito.mock(VaadinSession.class);
		
		Mockito.when(VaadinSession.getCurrent()).thenReturn(mockSession);
		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		
		assertEquals(PortalUtils.getUser(), null);
	}
	
	// FIXME
	@Ignore
	@Test 
	public void testWithLiferayInstance(){
		fail("Not yet implemented");
	}

	// FIXME
	@Ignore
	@Test
	public void testIsLiferayPortlet(){
		fail("Not yet implemented");
	}
	
	// FIXME
	@Ignore
	@Test
	public void testCurrentBasePath(){
		fail("Not yet implemented");
	}

}
