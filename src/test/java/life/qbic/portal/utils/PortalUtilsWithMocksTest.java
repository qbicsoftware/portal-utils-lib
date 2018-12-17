package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import org.junit.*;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.support.membermodification.MemberMatcher.field;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VaadinService.class, VaadinSession.class, VaadinRequest.class, PortalUtils.class})
public class PortalUtilsWithMocksTest {

	@Test
	public void testBuildImagePath(){
/*
		PowerMockito.mockStatic(VaadinSession.class);
		PowerMockito.mockStatic(VaadinService.class);

		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final VaadinSession mockSession = Mockito.mock(VaadinSession.class);

		Mockito.when(VaadinSession.getCurrent()).thenReturn(mockSession);
		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
*/

//Do not mock the complete class but only the function itself --> tested static function contains mocked static function
		//PowerMockito.mockStatic(PortalUtils.class);
		//Mockito.when(PortalUtils.isLiferayPortlet()).thenReturn(false);


		PowerMockito.stub(PowerMockito.method(PortalUtils.class, "isLiferayPortlet")).toReturn(false);
		System.out.println(PortalUtils.isLiferayPortlet()+" is liferay portlet?");


		assertEquals("/VAADIN/test", PortalUtils.buildImagePath("test"));
	}

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
