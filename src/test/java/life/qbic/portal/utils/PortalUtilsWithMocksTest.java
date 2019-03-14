package life.qbic.portal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import com.liferay.portal.model.User;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import java.net.URI;
import java.net.URL;

import life.qbic.portal.utils.user.UserRelated;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VaadinService.class, VaadinSession.class, VaadinRequest.class, PortalUtils.class, URI.class, UI.class, UserRelated.class})
@PowerMockIgnore("javax.management.*")
public class PortalUtilsWithMocksTest {

	@Test
	public void testBuildImagePathNoLiferayInstance(){

		PowerMockito.stub(PowerMockito.method(PortalUtils.class, "isLiferayPortlet")).toReturn(false);

		assertEquals("/VAADIN/test", PortalUtils.buildImagePath("test"));
	}

	@Test
	public void testBuildImagePathIsLiferayInstance() throws Exception {

		PowerMockito.stub(PowerMockito.method(PortalUtils.class, "isLiferayPortlet")).toReturn(true);

		URI realURI = new URL("https://portal-testing.qbic.uni-tuebingen.de").toURI();

		UI mockUI = Mockito.mock(UI.class, Mockito.RETURNS_DEEP_STUBS);
		final Page mockPage = Mockito.mock(Page.class);
		PowerMockito.stub(PowerMockito.method(UI.class, "getCurrent")).toReturn(mockUI);

		Mockito.when(mockUI.getPage()).thenReturn(mockPage);
		Mockito.when(mockPage.getLocation()).thenReturn(realURI);

		assertEquals(PortalUtils.buildImagePath("test"), "https://portal-testing.qbic.uni-tuebingen.de/portal-utils-lib/VAADIN/test");

	}

	@Test
	public void testGetAuthenticatedUser() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final User mockUser = Mockito.mock(User.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn("authenticated");
		Mockito.when(UserRelated.getLiferayUser("authenticated")).thenReturn(mockUser);

		assertSame(mockUser, PortalUtils.getUser());
	}

	@Test
	public void testGetUnauthenticatedUser() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn(null);

		assertSame(null, PortalUtils.getUser());
	}

	@Test
	public void testGetAuthenticatedScreenName() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final User mockUser = Mockito.mock(User.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn("authenticated");
		Mockito.when(UserRelated.getLiferayUser("authenticated")).thenReturn(mockUser);
		Mockito.when(mockUser.getScreenName()).thenReturn("Mr. Secure");

		assertEquals("Mr. Secure", PortalUtils.getScreenName());
	}

	@Test
	public void testGetUnauthenticatedScreenName() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn(null);

		assertEquals(null, PortalUtils.getScreenName());
	}

	@Test
	public void testGetAuthenticatedNonNullScreenName() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);
		final User mockUser = Mockito.mock(User.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn("authenticated");
		Mockito.when(UserRelated.getLiferayUser("authenticated")).thenReturn(mockUser);
		Mockito.when(mockUser.getScreenName()).thenReturn("Mr. Secure");

		assertEquals("Mr. Secure", PortalUtils.getNonNullScreenName());
	}

	@Test
	public void testGetUnauthenticatedNonNullScreenName() {
		final VaadinRequest mockRequest = Mockito.mock(VaadinRequest.class);

		PowerMockito.mockStatic(VaadinService.class);
		PowerMockito.mockStatic(UserRelated.class);

		Mockito.when(VaadinService.getCurrentRequest()).thenReturn(mockRequest);
		Mockito.when(mockRequest.getRemoteUser()).thenReturn(null);

		assertEquals("Anonymous", PortalUtils.getNonNullScreenName());
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
