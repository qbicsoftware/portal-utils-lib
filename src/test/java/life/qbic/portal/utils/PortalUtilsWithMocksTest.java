package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.*;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VaadinService.class, VaadinSession.class, VaadinRequest.class, PortalUtils.class, URI.class, UI.class})
public class PortalUtilsWithMocksTest {

	@Test
	public void testBuildImagePathNoLiferayInstance(){

		PowerMockito.stub(PowerMockito.method(PortalUtils.class, "isLiferayPortlet")).toReturn(false);
		//System.out.println(PortalUtils.isLiferayPortlet()+" is liferay portlet?");


		assertEquals("/VAADIN/test", PortalUtils.buildImagePath("test"));
	}

	@Test
	public void testBuildImagePathIsLiferayInstance() throws Exception {

		PowerMockito.stub(PowerMockito.method(PortalUtils.class, "isLiferayPortlet")).toReturn(true);

		URI realURI = new URL("https://portal-testing.qbic.uni-tuebingen.de").toURI();

		//PowerMockito.mockStatic(UI.class);
		UI mockUI = Mockito.mock(UI.class, Mockito.RETURNS_DEEP_STUBS);
		final Page mockPage = Mockito.mock(Page.class);
		PowerMockito.stub(PowerMockito.method(UI.class, "getCurrent")).toReturn(mockUI);

		Mockito.when(mockUI.getPage()).thenReturn(mockPage);
		Mockito.when(mockPage.getLocation()).thenReturn(realURI);

		assertEquals(PortalUtils.buildImagePath("test"), "https://portal-testing.qbic.uni-tuebingen.de/portal-utils-lib/VAADIN/test");

	}

	@Test
	public void testNoLiferayInstance() {

		PowerMockito.mockStatic(VaadinSession.class);
		PowerMockito.mockStatic(VaadinService.class);

		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final VaadinSession mockSession = Mockito.mock(VaadinSession.class);

		Mockito.when(VaadinSession.getCurrent()).thenReturn(mockSession);
		//VaadinSession.getCurrent().getService();
		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		//String remoteuser = VaadinService.getCurrentRequest().getRemoteUser();

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
