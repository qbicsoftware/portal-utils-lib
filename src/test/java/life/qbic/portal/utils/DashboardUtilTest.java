package life.qbic.portal.utils;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import life.qbic.portal.utils.DashboardUtil;


public class DashboardUtilTest {

  @Test
  public void testHumanReadableByteCount() {
    //bytes
    String target = DashboardUtil.humanReadableByteCount(0, false);    
    assertThat(target).isEqualTo("0 B");
    target = DashboardUtil.humanReadableByteCount(0, true);    
    assertThat(target).isEqualTo("0 B");

    target = DashboardUtil.humanReadableByteCount(24, false);    
    assertThat(target).isEqualTo("24 B");
    
    target = DashboardUtil.humanReadableByteCount(24, true);    
    assertThat(target).isEqualTo("24 B");
    
    //k bytes
    
    target = DashboardUtil.humanReadableByteCount(110592, false);    
    assertThat(target).isEqualTo("108.0 KiB");
 
    target = DashboardUtil.humanReadableByteCount(110592, true);    

    assertThat(target).isEqualTo("110.6 kB");

    
    //M bytes
    target = DashboardUtil.humanReadableByteCount(6787601, false);    
    assertThat(target).isEqualTo("6.5 MiB");
    
    
    //G bytes
    
    target = DashboardUtil.humanReadableByteCount(8406772480L, false);    
    assertThat(target).isEqualTo("7.8 GiB");
    
    target = DashboardUtil.humanReadableByteCount(106344247601L, false);    
    assertThat(target).isEqualTo("99.0 GiB");

  }

}
