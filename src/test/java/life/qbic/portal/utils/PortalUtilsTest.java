package life.qbic.portal.utils;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;



/**
 * Tests without need for mocking.
 */

public class PortalUtilsTest {


  @Test
  public void testHumanReadableByteCount() {
    //bytes
    String target = PortalUtils.humanReadableByteCount(0, false);    
    assertThat(target).isEqualTo("0 B");
    target = PortalUtils.humanReadableByteCount(0, true);    
    assertThat(target).isEqualTo("0 B");

    target = PortalUtils.humanReadableByteCount(24, false);    
    assertThat(target).isEqualTo("24 B");
    
    target = PortalUtils.humanReadableByteCount(24, true);    
    assertThat(target).isEqualTo("24 B");
    
    //k bytes

    target = PortalUtils.humanReadableByteCount(110592, false);    
    assertThat(target).isEqualTo("108.0 KiB");
 
    target = PortalUtils.humanReadableByteCount(110592, true);    

    assertThat(target).isEqualTo("110.6 kB");

    
    //M bytes
    target = PortalUtils.humanReadableByteCount(6787601, false);    
    assertThat(target).isEqualTo("6.5 MiB");
    
    
    //G bytes
    
    target = PortalUtils.humanReadableByteCount(8406772480L, false);    
    assertThat(target).isEqualTo("7.8 GiB");
    
    target = PortalUtils.humanReadableByteCount(106344247601L, false);    
    assertThat(target).isEqualTo("99.0 GiB");

  }


}
